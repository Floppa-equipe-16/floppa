package ulaval.glo2003.api.offer;

import java.util.Objects;
import ulaval.glo2003.domain.offer.Offer;

public class OfferResponse {
    public String username;
    public Double amount;
    public String message;
    public String createdAt;

    public OfferResponse() {}

    public OfferResponse(Offer offer) {
        username = offer.getUsername();
        amount = offer.getAmount();
        message = offer.getMessage();
        createdAt = offer.getCreatedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferResponse) {
            OfferResponse offerResponse = ((OfferResponse) o);
            return isEqualsTo(offerResponse);
        } else return false;
    }

    private boolean isEqualsTo(OfferResponse response) {
        return Objects.equals(username, response.username)
                && Objects.equals(amount, response.amount)
                && Objects.equals(message, response.message)
                && Objects.equals(createdAt, response.createdAt);
    }
}
