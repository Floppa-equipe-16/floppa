package ulaval.glo2003.domain.notification;

public class EmailHost {
    public final String smtpDomain;
    public final String port;

    public EmailHost(String smtpDomain, String port) {
        this.smtpDomain = smtpDomain;
        this.port = port;
    }
}
