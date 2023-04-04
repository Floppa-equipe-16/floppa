package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;

class SellingServiceFactoryTest {

    @Test
    public void canCreateInMemory() {
        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService service = factory.create();

        assertThat(service).isNotNull();
    }

    @Test
    public void canCreateWithMongo() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(5000, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(5000, TimeUnit.MILLISECONDS))
                .build());
        Datastore datastore = Morphia.createDatastore(client, "floppa-dev");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();
        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService service = factory.create(datastore);

        assertThat(service).isNotNull();
    }
}
