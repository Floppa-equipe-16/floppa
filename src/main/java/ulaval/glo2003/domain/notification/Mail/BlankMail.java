package ulaval.glo2003.domain.notification.Mail;

public class BlankMail extends Mail {
    public BlankMail(String to) {
        this.sendTo = to;
        this.subject = "";
        this.text = "";
    }
}
