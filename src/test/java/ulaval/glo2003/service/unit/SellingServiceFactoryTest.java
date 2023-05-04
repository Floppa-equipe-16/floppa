package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

import dev.morphia.Datastore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;
import ulaval.glo2003.utils.MongoTestUtils;

class SellingServiceFactoryTest {
    @Mock
    private NotificationService notificationService = mock(NotificationService.class);

    @Test
    public void canCreateInMemory() {

        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService service = factory.create(notificationService);

        assertThat(service).isNotNull();
    }

    @Test
    public void canCreateWithMongo() {
        Datastore datastore = MongoTestUtils.createLocalDatastore();
        SellingServiceFactory factory = new SellingServiceFactory();

        SellingService service = factory.create(datastore, notificationService);

        assertThat(service).isNotNull();
    }
}
