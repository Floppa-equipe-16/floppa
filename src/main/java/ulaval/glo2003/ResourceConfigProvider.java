package ulaval.glo2003;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.concurrent.TimeUnit;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.HealthResource;
import ulaval.glo2003.api.LostResource;
import ulaval.glo2003.api.exceptionMappers.NotFoundExceptionMapper;
import ulaval.glo2003.api.exceptionMappers.ParamExceptionMapper;
import ulaval.glo2003.api.offer.OfferResource;
import ulaval.glo2003.api.product.ProductResource;
import ulaval.glo2003.api.seller.SellerResource;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;
import ulaval.glo2003.service.notification.EmailHost;
import ulaval.glo2003.service.notification.SessionException;

public class ResourceConfigProvider {

    private final int TIMEOUT = 5000;

    public ResourceConfig provide(Boolean localDB) throws SessionException {
        MongoClient client;
        Datastore datastore;

        if (localDB) {
            client = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                    .applyToConnectionPoolSettings(
                            builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                    .build());
            datastore = Morphia.createDatastore(client, "floppa-dev");
        } else {
            client = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                    .applyToConnectionPoolSettings(
                            builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                    .applyConnectionString(new ConnectionString(System.getenv("FLOPPA_MONGO_CLUSTER_URL")))
                    .build());
            databaseHealthCheck(client);
            datastore = Morphia.createDatastore(client, System.getenv("FLOPPA_MONGO_DATABASE"));
        }

        EmailHost emailHost = new EmailHost("floppanotification@gmail.com", "vexwtppdwslsfcra");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        SellingServiceFactory factory = new SellingServiceFactory();
        SellingService sellingService = factory.create(datastore, emailHost);

        return new ResourceConfig()
                .register(new HealthResource(client))
                .register(LostResource.class)
                .register(new SellerResource(sellingService))
                .register(new ProductResource(sellingService))
                .register(new OfferResource(sellingService))
                .register(ParamExceptionMapper.class)
                .register(NotFoundExceptionMapper.class)
                .register(JacksonFeature.class);
    }

    private void databaseHealthCheck(MongoClient client) {
        client.listDatabaseNames().first();
    }
}
