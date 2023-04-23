package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;

public class NotificationServiceFactoryTest {

    private final NotificationServiceFactory factory = new NotificationServiceFactory();

    @Test
    public void canCreateNotificationService() {
        NotificationService service = factory.create(false);

        assertThat(service).isNotNull();
    }

    @Test
    public void canGetEmailHost() {
        EmailHost emailHost = factory.getEmailHost();

        assertThat(emailHost.port).isNotNull();
        assertThat(emailHost.smtpDomain).isNotNull();
    }

    @Test
    public void canGetEmailAuthentication() {
        EmailAuthentication emailAuthentication = factory.getEmailAuthentication();

        assertThat(emailAuthentication.email).isNotNull();
        assertThat(emailAuthentication.password).isNotNull();
    }
}
