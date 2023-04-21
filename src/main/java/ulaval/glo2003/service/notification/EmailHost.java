package ulaval.glo2003.service.notification;

public class EmailHost {
    public final String email;
    public final String password;

    public EmailHost(String hostEmail, String hostPassword) {
        email = hostEmail;
        password = hostPassword;
    }
}
