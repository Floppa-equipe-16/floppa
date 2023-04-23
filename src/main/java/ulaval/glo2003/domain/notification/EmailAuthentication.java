package ulaval.glo2003.domain.notification;

public class EmailAuthentication {

    public final String email;
    public final String password;

    public EmailAuthentication(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
