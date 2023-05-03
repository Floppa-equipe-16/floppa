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
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;

public class ResourceConfigProvider {

    private final int TIMEOUT = 5000;

    public ResourceConfig provide(Boolean isDevDB) {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyConnectionString(new ConnectionString(EnvironmentVariable.getFloppaMongoClusterUrl()))
                .build());

        Datastore datastore =
                Morphia.createDatastore(client, isDevDB ? "floppa-dev" : EnvironmentVariable.getFloppaMongoDatabase());
        databaseHealthCheck(client);

        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        NotificationServiceFactory notificationFactory = new NotificationServiceFactory();
        NotificationService notificationService = notificationFactory.create(true);

        SellingServiceFactory factory = new SellingServiceFactory();
        SellingService sellingService = factory.create(datastore, notificationService);

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
