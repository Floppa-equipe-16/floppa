package ulaval.glo2003.api.offer;

import ulaval.glo2003.domain.offer.Offer;

public class SelectedOfferResponse {
    public String username;
    public Double amount;

    public SelectedOfferResponse(Offer offer) {
        username = offer.getUsername();
        amount = offer.getAmount();
    }
}
