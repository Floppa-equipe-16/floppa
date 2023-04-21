package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.service.notification.EmailHost;
import ulaval.glo2003.service.notification.Mail.BlankMail;
import ulaval.glo2003.service.notification.Notification;
import ulaval.glo2003.service.notification.SessionException;
import ulaval.glo2003.utils.EmailHostTestUtils;

public class NotificationTest {

    @Test
    public void constructorHostValid() {
        assertDoesNotThrow(() -> new Notification(EmailHostTestUtils.emailHost, true));
    }

    @Test
    public void constructorHostInvalid() {
        EmailHost invalidHost = new EmailHost("floppanotification@gmail.com", "notGood");

        assertThrows(Exception.class, () -> new Notification(invalidHost, true));
    }

    @Test
    public void canSendMail() {
        Notification notification;
        try {
            notification = new Notification(EmailHostTestUtils.emailHost, true);
        } catch (SessionException ignored) {
            fail("Should not throw exception check test : constructorHostValid");
            return;
        }
        BlankMail mail = new BlankMail(EmailHostTestUtils.emailHost.email);

        boolean result = notification.sendEmail(mail);

        assertThat(result).isTrue();
    }
}
