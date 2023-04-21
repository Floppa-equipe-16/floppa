package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.notification.Mail.BlankMail;
import ulaval.glo2003.service.notification.SessionException;
import ulaval.glo2003.utils.EmailHostTestUtils;

public class NotificationServiceTest {

    @Test
    public void constructorDoesNotThrow() {
        assertDoesNotThrow(
                () -> new NotificationService(EmailHostTestUtils.emailHost, true),
                "This should not throw check 'Notification' constructor Test");
    }

    @Test
    public void canAddEmailToQueue() throws SessionException {
        NotificationService service = new NotificationService(EmailHostTestUtils.emailHost, false);
        BlankMail mail = new BlankMail("");

        assertThat(service.isQueueEmpty()).isTrue();

        service.addMailToQueue(mail);

        assertThat(service.isQueueEmpty()).isFalse();
    }

    @Test
    public void canStartThread() throws SessionException, InterruptedException {
        NotificationService service = new NotificationService(EmailHostTestUtils.emailHost, false);
        assertThat(service.isThreadAlive()).isFalse();

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);

        assertThat(service.isThreadAlive()).isTrue();
    }

    @Test
    public void canStopThread() throws SessionException, InterruptedException {
        NotificationService service = new NotificationService(EmailHostTestUtils.emailHost, false);
        assertThat(service.isThreadAlive()).isFalse();

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);
        service.stopThread();
        TimeUnit.MILLISECONDS.sleep(100);

        assertThat(service.isThreadAlive()).isFalse();
    }

    @Test
    void canSendMail() throws SessionException, InterruptedException {
        NotificationService service = new NotificationService(EmailHostTestUtils.emailHost, false);
        BlankMail mail = new BlankMail("");

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);
        service.addMailToQueue(mail);
        service.stopThread();
        service.joinThread();

        assertThat(service.isQueueEmpty()).isTrue();
    }
}
