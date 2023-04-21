package ulaval.glo2003.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import ulaval.glo2003.service.notification.EmailHost;
import ulaval.glo2003.service.notification.Mail.BlankMail;
import ulaval.glo2003.service.notification.Mail.Mail;
import ulaval.glo2003.service.notification.Notification;
import ulaval.glo2003.service.notification.SessionException;

public class NotificationService {

    private boolean running;
    private final Thread thread;
    private final Notification notification;
    private final BlockingQueue<Mail> fifoQueue;

    public NotificationService(EmailHost host, boolean checkSession) throws SessionException {
        notification = new Notification(host, checkSession);
        fifoQueue = new LinkedBlockingQueue<Mail>();
        thread = new Thread(null, this::sendMailThread, "Notification-Thread");
        thread.setDaemon(true);
        running = false;
    }

    private void sendMailThread() {
        while (running) {
            try {
                Mail mail = fifoQueue.take();

                if (mail.getClass() == BlankMail.class) continue;

                notification.sendEmail(mail);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void addMailToQueue(Mail mail) {
        fifoQueue.add(mail);
    }

    public boolean isQueueEmpty() {
        return fifoQueue.isEmpty();
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

    public void stopThread() {
        if (running) {
            running = false;
            if (isQueueEmpty()) addMailToQueue(new BlankMail(""));
        }
    }
}
