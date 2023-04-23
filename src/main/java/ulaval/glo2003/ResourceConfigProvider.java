package ulaval.glo2003;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;

public class ResourceConfigProvider {

    private final int TIMEOUT = 5000;

    public ResourceConfig provide(Boolean localDB) {
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

        Properties notificationProp = getNotificationProp();
        EmailHost emailHost = getEmailHostFromProp(notificationProp);
        EmailAuthentication emailAuthentication = getEmailAuthentication();
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        NotificationServiceFactory notificationFactory = new NotificationServiceFactory();
        NotificationService notificationService = notificationFactory.create(emailHost, emailAuthentication, true);

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

    private Properties getNotificationProp() {

        try (InputStream input =
                getClass().getClassLoader().getResourceAsStream("EmailNotificationConfig.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                throw new RuntimeException("Config file not found");
            }

            prop.load(input);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static EmailAuthentication getEmailAuthentication() {
        String email = System.getenv("FLOPPA_HOST_EMAIL");
        String password = System.getenv("FLOPPA_HOST_PASSWORD");
        return new EmailAuthentication(email, password);
    }

    public static EmailHost getEmailHostFromProp(Properties properties) {
        String smtpDomain = properties.getProperty("smtpDomain", "");
        String domainPort = properties.getProperty("domainPort", "");
        return new EmailHost(smtpDomain, domainPort);
    }

    private void databaseHealthCheck(MongoClient client) {
        client.listDatabaseNames().first();
    }
}
