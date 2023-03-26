package ulaval.glo2003.domain.offer;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.concurrent.TimeUnit;

public class MongoOfferRepositoryTest extends IOfferRepositoryTest {

    @Override
    protected IOfferRepository createRepository() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(5000, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(5000, TimeUnit.MILLISECONDS))
                .build());
        Datastore datastore = Morphia.createDatastore(client, "floppa-dev");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        return new MongoOfferRepository(datastore);
    }
}
