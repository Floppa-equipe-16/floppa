package ulaval.glo2003.domain.infrastructure.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.ISellerRepositoryTest;

import java.util.concurrent.TimeUnit;

public class MongoSellerRepositoryTest extends ISellerRepositoryTest {

    private final int TIMEOUT = 5000;

    @Override
    protected ISellerRepository createRepository() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                .build());
        Datastore datastore = Morphia.createDatastore(client, "floppa-dev");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        return new MongoSellerRepository(datastore);
    }
}
