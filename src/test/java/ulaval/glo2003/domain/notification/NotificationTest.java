package ulaval.glo2003.domain.notification;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.EnvironmentVariable;
import ulaval.glo2003.domain.notification.Mail.BlankMail;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.utils.EnvironmentVarMock;

public class NotificationTest {

    private EmailHost emailHost;
    private EmailAuthentication emailAuthentication;

    @BeforeEach
    public void setUp() {

        NotificationServiceFactory factory = new NotificationServiceFactory();
        emailHost = factory.getEmailHost();
        emailAuthentication = factory.getEmailAuthentication();

    }

    @Test
    public void constructorHostValid() {
        assertDoesNotThrow(() -> new Notification(emailHost, emailAuthentication, true));
    }

    @Test
    public void constructorHostInvalid() {
        EmailAuthentication invalidAuthentication = new EmailAuthentication("Floppanotification@gmail.com", "notGood");
        assertThrows(Exception.class, () -> new Notification(emailHost, invalidAuthentication, true));
    }

    @Test
    public void canSendMail() {
        Notification notification;
        try {
            notification = new Notification(emailHost, emailAuthentication, true);
        } catch (SessionException ignored) {
            fail("Should not throw exception check test : constructorHostValid");
            return;
        }
        BlankMail mail = new BlankMail("floppanotification@gmail.com");

        boolean result = notification.sendEmail(mail);

        if (isEnvVarSet()){
            assertThat(result).isFalse();
        }
        else {
            assertThat(result).isTrue();
        }
    }

    private boolean isEnvVarSet(){
        return emailAuthentication == null;
    }
}
