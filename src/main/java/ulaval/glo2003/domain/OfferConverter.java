package ulaval.glo2003.domain;

import ulaval.glo2003.api.offer.OfferRequest;

public class OfferConverter {

    public static Offer offerRequestToOffer(String xBuyerUsername, OfferRequest offerRequest) {
        return new Offer(xBuyerUsername, offerRequest.amount, offerRequest.message);
    }
}
