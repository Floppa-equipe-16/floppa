package ulaval.glo2003.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.domain.notification.Notification;

public class EmailTestUtils {
    public final EmailHost emailHost;

    public final EmailAuthentication emailAuthentication;

    public EmailTestUtils() {
        Properties prop = getNotificationProp();
        emailHost = Notification.getEmailHostFromProp(prop);
        emailAuthentication = Notification.getEmailAuthentificationFromProp(prop);
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
