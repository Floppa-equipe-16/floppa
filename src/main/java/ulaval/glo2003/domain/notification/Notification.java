package ulaval.glo2003.domain.notification;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import ulaval.glo2003.domain.notification.Mail.BlankMail;
import ulaval.glo2003.domain.notification.Mail.Mail;

public class Notification {

    private final Session session;


    public Notification(EmailHost emailHost, EmailAuthentication emailAuthentication, boolean checkSession) {
        Properties properties = getMailProp(emailHost);

        if (emailAuthentication != null){
            session = Session.getInstance(properties, getAuthenticator(emailAuthentication));

            if (checkSession)
                checkConnection(emailAuthentication.email);
        }
        else session = null;

    }

    public boolean sendEmail(Mail message) {
        if (session == null)
            return false;
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

    private void checkConnection(String sendTo) {
        BlankMail mail = new BlankMail(sendTo);
        if (!sendEmail(mail)) throw new SessionException();
    }

    private Authenticator getAuthenticator(EmailAuthentication emailAuthentication) {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAuthentication.email, emailAuthentication.password);
            }
        };
    }

    private Properties getMailProp(EmailHost emailHost) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", emailHost.smtpDomain);
        prop.put("mail.smtp.port", emailHost.port);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");
        return prop;
    }
}
