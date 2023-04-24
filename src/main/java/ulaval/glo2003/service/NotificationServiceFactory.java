package ulaval.glo2003.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ulaval.glo2003.EnvironmentVariable;
import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.domain.notification.Notification;

public class NotificationServiceFactory {

    public NotificationService create(boolean sessionCheck) {
        EmailHost emailHost = getEmailHost();
        EmailAuthentication emailAuthentication = getEmailAuthentication();
        Notification notification = new Notification(emailHost, emailAuthentication, sessionCheck);
        return new NotificationService(notification);
    }

    public EmailAuthentication getEmailAuthentication() {
        String email = EnvironmentVariable.getFloppaHostEmail();
        String password = EnvironmentVariable.getFloppaHostPassword();
        EmailAuthentication authentication;

        if (email == null || password == null)
            authentication = null;
        else
            authentication = new EmailAuthentication(email, password);

        return authentication;
    }

    public EmailHost getEmailHost() {
        Properties properties = getNotificationProp();
        String smtpDomain = properties.getProperty("smtpDomain", "");
        String domainPort = properties.getProperty("domainPort", "");
        return new EmailHost(smtpDomain, domainPort);
    }

    private Properties getNotificationProp() {

        try (InputStream input =
                getClass().getClassLoader().getResourceAsStream("EmailNotificationConfig.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                throw new RuntimeException("Config file not found");
            }

            prop.load(input);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
