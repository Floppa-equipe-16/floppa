package ulaval.glo2003.service;

import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.domain.notification.Notification;

public class NotificationServiceFactory {

    public NotificationService create(EmailHost host, boolean sessionCheck) {
        Notification notification = new Notification(host, sessionCheck);
        return new NotificationService(notification);
    }
}
