package ulaval.glo2003.service.notification;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import ulaval.glo2003.service.notification.Mail.BlankMail;
import ulaval.glo2003.service.notification.Mail.Mail;

public class Notification {

    private final Session session;
    private static final String SMTP_DOMAIN = "smtp.gmail.com";
    private static final String PORT = "465";

    public Notification(EmailHost emailHost, boolean checkSession) throws SessionException {
        Properties properties = getMailProp();
        session = Session.getInstance(properties, getAuthenticator(emailHost));

        if (checkSession) checkConnection(emailHost.email);
    }

    public boolean sendEmail(Mail message) {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(message.sendTo));
            mimeMessage.setSubject(message.subject);
            mimeMessage.setText(message.text);

            Transport.send(mimeMessage);
            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }

    private void checkConnection(String sendTo) throws SessionException {
        BlankMail mail = new BlankMail(sendTo);
        if (!sendEmail(mail)) throw new SessionException();
    }

    private Authenticator getAuthenticator(EmailHost emailHost) {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailHost.email, emailHost.password);
            }
        };
    }

    private Properties getMailProp() {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_DOMAIN);
        prop.put("mail.smtp.port", PORT);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");
        return prop;
    }
}
