package ulaval.glo2003.domain.notification;

public class SessionException extends RuntimeException {

    public SessionException() {
        super("Session failed to connect");
    }
}
