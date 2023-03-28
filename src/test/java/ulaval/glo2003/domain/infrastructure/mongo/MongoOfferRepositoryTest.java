package ulaval.glo2003.domain.infrastructure.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.IOfferRepositoryTest;

import java.util.concurrent.TimeUnit;

public class MongoOfferRepositoryTest extends IOfferRepositoryTest {

    private final int TIMEOUT = 5000;

    @Override
    protected IOfferRepository createRepository() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                .build());
        Datastore datastore = Morphia.createDatastore(client, "floppa-dev");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        return new MongoOfferRepository(datastore);
    }
}
