package ulaval.glo2003.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import ulaval.glo2003.domain.notification.Mail.BlankMail;
import ulaval.glo2003.domain.notification.Mail.Mail;
import ulaval.glo2003.domain.notification.Notification;

public class NotificationService {

    private boolean running;
    private final Thread thread;
    private final Notification notification;
    private final BlockingQueue<Mail> UnsentMails;

    public NotificationService(Notification notification) {
        this.notification = notification;
        UnsentMails = new LinkedBlockingQueue<>();
        thread = new Thread(null, this::sendMailThread, "Notification-Thread");
        thread.setDaemon(true);
        running = false;
    }

    private void sendMailThread() {
        while (running) {
            try {
                Mail mail = UnsentMails.take();

                if (mail.getClass() == BlankMail.class) continue;

                notification.sendEmail(mail);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void addMailToQueue(Mail mail) {
        UnsentMails.add(mail);
    }

    public boolean isQueueEmpty() {
        return UnsentMails.isEmpty();
    }

    public boolean isThreadAlive() {
        return thread.isAlive();
    }

    public void startThread() {
        if (!running) {
            running = true;
            thread.start();
        }
    }

    public void joinThread() throws InterruptedException {
        thread.join();
    }

    public void stopThread() throws InterruptedException {
        if (running) {
            running = false;
            if (isQueueEmpty()) addMailToQueue(new BlankMail(""));
        }
        joinThread();
    }
}
