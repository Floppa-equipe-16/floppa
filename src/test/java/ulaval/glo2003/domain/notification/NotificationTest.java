package ulaval.glo2003.domain.notification;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.notification.Mail.BlankMail;
import ulaval.glo2003.utils.EmailTestUtils;

public class NotificationTest {

    private final EmailTestUtils emailTestUtils = new EmailTestUtils();

    @Test
    public void constructorHostValid() {
        assertDoesNotThrow(() -> new Notification(emailTestUtils.emailHost, emailTestUtils.emailAuthentication, true));
    }

    @Test
    public void constructorHostInvalid() {
        EmailAuthentication invalidAuthentication =
                new EmailAuthentication(emailTestUtils.emailAuthentication.email, "notGood");

        assertThrows(Exception.class, () -> new Notification(emailTestUtils.emailHost, invalidAuthentication, true));
    }

    @Test
    public void canSendMail() {
        Notification notification;
        try {
            notification = new Notification(emailTestUtils.emailHost, emailTestUtils.emailAuthentication, true);
        } catch (SessionException ignored) {
            fail("Should not throw exception check test : constructorHostValid");
            return;
        }
        BlankMail mail = new BlankMail(emailTestUtils.emailAuthentication.email);

        boolean result = notification.sendEmail(mail);

        assertThat(result).isTrue();
    }
}
