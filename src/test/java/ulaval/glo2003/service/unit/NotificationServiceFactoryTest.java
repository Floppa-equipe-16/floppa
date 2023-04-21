package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.utils.EmailHostTestUtils;

public class NotificationServiceFactoryTest {
    @Test
    public void canCreateNotificationService() {
        NotificationServiceFactory factory = new NotificationServiceFactory();

        NotificationService service = factory.create(EmailHostTestUtils.emailHost, false);

        assertThat(service).isNotNull();
    }
}
