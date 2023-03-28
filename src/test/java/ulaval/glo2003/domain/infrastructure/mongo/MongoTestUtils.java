package ulaval.glo2003.domain.infrastructure.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.concurrent.TimeUnit;

public class MongoTestUtils {

    private static final int TIMEOUT = 5000;

    public static Datastore createLocalDatastore() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(TIMEOUT, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(TIMEOUT, TimeUnit.MILLISECONDS))
                .build());
        Datastore datastore = Morphia.createDatastore(client, "floppa-dev");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        return datastore;
    }
}
