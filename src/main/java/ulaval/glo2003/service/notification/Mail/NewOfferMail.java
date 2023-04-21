package ulaval.glo2003.service.notification.Mail;

public class NewOfferMail extends Mail {
    public NewOfferMail(String buyerUsername, Double price, String productId, String to) {
        subject = "You have a new offer";
        text = buyerUsername + ", the buyer, has just submitted a new offer of $"
                + price.toString()
                + " for the product with the ID '"
                + productId + "'.";
        sendTo = to;
    }
}
