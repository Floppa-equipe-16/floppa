package ulaval.glo2003.service.notification;

public class SessionException extends Exception {

    public SessionException() {
        super("Session fail to send message");
    }
}
