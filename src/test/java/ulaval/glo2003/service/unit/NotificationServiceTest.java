package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.notification.Mail.BlankMail;
import ulaval.glo2003.domain.notification.Notification;
import ulaval.glo2003.domain.notification.SessionException;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.utils.EmailTestUtils;

public class NotificationServiceTest {

    private Notification notification;
    private final EmailTestUtils emailTestUtils = new EmailTestUtils();

    @BeforeEach
    public void setUp() throws SessionException {
        notification = new Notification(emailTestUtils.emailHost, emailTestUtils.emailAuthentication, true);
    }

    @Test
    public void constructorDoesNotThrow() {
        assertDoesNotThrow(
                () -> new NotificationService(notification),
                "This should not throw check 'Notification' constructor Test");
    }

    @Test
    public void canAddEmailToQueue() {
        NotificationService service = new NotificationService(notification);
        BlankMail mail = new BlankMail("");

        assertThat(service.isQueueEmpty()).isTrue();

        service.addMailToQueue(mail);

        assertThat(service.isQueueEmpty()).isFalse();
    }

    @Test
    public void canStartThread() throws InterruptedException {
        NotificationService service = new NotificationService(notification);
        assertThat(service.isThreadAlive()).isFalse();

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);

        assertThat(service.isThreadAlive()).isTrue();
    }

    @Test
    public void canStopThread() throws InterruptedException {
        NotificationService service = new NotificationService(notification);
        assertThat(service.isThreadAlive()).isFalse();

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);
        service.stopThread();
        TimeUnit.MILLISECONDS.sleep(100);

        assertThat(service.isThreadAlive()).isFalse();
    }

    @Test
    void canSendMail() throws InterruptedException {
        NotificationService service = new NotificationService(notification);
        BlankMail mail = new BlankMail("");

        service.startThread();
        TimeUnit.MILLISECONDS.sleep(100);
        service.addMailToQueue(mail);
        service.stopThread();
        service.joinThread();

        assertThat(service.isQueueEmpty()).isTrue();
    }
}
