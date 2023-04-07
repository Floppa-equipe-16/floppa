package ulaval.glo2003.utils.equals;

import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OfferResponse;
import ulaval.glo2003.domain.offer.Offer;

public class OfferEquals {

    public static boolean OfferRequestEqualsOffer(OfferRequest request, Offer offer) {
        return request.amount.equals(offer.getAmount()) && request.message.equals(offer.getMessage());
    }

    public static boolean OfferResponseEqualsOffer(OfferResponse response, Offer offer) {
        return response.message.equals(offer.getMessage())
                && response.username.equals(offer.getUsername())
                && response.amount.equals(offer.getAmount())
                && response.createdAt.equals(offer.getCreatedAt());
    }
}
