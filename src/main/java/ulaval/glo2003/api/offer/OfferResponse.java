package ulaval.glo2003.api.offer;

import ulaval.glo2003.domain.offer.Offer;

public class OfferResponse {
    public String username;
    public Double amount;
    public String message;
    public String createdAt;

    public OfferResponse(Offer offer) {
        username = offer.getUsername();
        amount = offer.getAmount();
        message = offer.getMessage();
        createdAt = offer.getCreatedAt();
    }

    private boolean isOfferResponseEquals(OfferResponse response) {
        return username.equals(response.username)
                && amount.equals(response.amount)
                && message.equals(response.message)
                && createdAt.equals(response.createdAt);
    }

    private boolean isOfferEquals(Offer offer) {
        return username.equals(offer.getUsername())
                && amount.equals(offer.getAmount())
                && message.equals(offer.getMessage())
                && createdAt.equals(offer.getCreatedAt());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferResponse) {
            OfferResponse offerResponse = ((OfferResponse) o);
            return isOfferResponseEquals(offerResponse);
        } else if (o instanceof Offer) {
            Offer offer = ((Offer) o);
            return isOfferEquals(offer);
        } else return false;
    }
}
