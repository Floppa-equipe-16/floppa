package ulaval.glo2003;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
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

public class Main {

    private static final int TIMEOUT = 5000;

    public static void main(String[] args) throws IOException {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyConnectionString(new ConnectionString(System.getenv("FLOPPA_MONGO_CLUSTER_URL")))
                .build());
        databaseHealthCheck(client);
        Datastore datastore = Morphia.createDatastore(client, System.getenv("FLOPPA_MONGO_DATABASE"));
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        SellingServiceFactory factory = new SellingServiceFactory();
        SellingService sellingService = factory.create(datastore);

        HealthResource healthResource = new HealthResource(client);
        LostResource lostResource = new LostResource();
        SellerResource sellerResource = new SellerResource(sellingService);
        ProductResource productResource = new ProductResource(sellingService);
        OfferResource offerResource = new OfferResource(sellingService);

        ParamExceptionMapper paramExceptionMapper = new ParamExceptionMapper();
        NotFoundExceptionMapper notFoundExceptionMapper = new NotFoundExceptionMapper();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(healthResource)
                .register(lostResource)
                .register(sellerResource)
                .register(productResource)
                .register(offerResource)
                .register(paramExceptionMapper)
                .register(notFoundExceptionMapper)
                .register(JacksonFeature.class);

        URI uri = URI.create("http://0.0.0.0:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }

    private static void databaseHealthCheck(MongoClient client) {
        client.listDatabaseNames().first();
    }
}
