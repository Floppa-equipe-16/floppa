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
}
