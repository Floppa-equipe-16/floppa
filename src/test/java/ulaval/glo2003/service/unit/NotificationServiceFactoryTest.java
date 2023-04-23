package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.utils.EmailTestUtils;

public class NotificationServiceFactoryTest {

    private final EmailTestUtils emailTestUtils = new EmailTestUtils();

    @Test
    public void canCreateNotificationService() {
        NotificationServiceFactory factory = new NotificationServiceFactory();

        NotificationService service =
                factory.create(emailTestUtils.emailHost, emailTestUtils.emailAuthentication, false);

        assertThat(service).isNotNull();
    }
}
