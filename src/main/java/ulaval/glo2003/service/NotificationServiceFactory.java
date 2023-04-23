package ulaval.glo2003.service;

import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.domain.notification.Notification;

public class NotificationServiceFactory {

    public NotificationService create(EmailHost host, EmailAuthentication authentication, boolean sessionCheck) {
        Notification notification = new Notification(host, authentication, sessionCheck);
        return new NotificationService(notification);
    }
}
