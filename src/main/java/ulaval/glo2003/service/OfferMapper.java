package ulaval.glo2003.service;

import java.util.List;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OffersRepositoryResponse;
import ulaval.glo2003.domain.offer.Offer;

public class OfferMapper {

    public static Offer requestToOffer(String productId, String buyerName, OfferRequest offerRequest) {
        return new Offer(productId, buyerName, offerRequest.amount, offerRequest.message);
    }

    public static OffersRepositoryResponse offersListToRepositoryResponse(List<Offer> offers) {
        OffersRepositoryResponse offersRepositoryResponse = new OffersRepositoryResponse();

        offersRepositoryResponse.count = offers.size();
        Double avgAmount =
                offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
        if (Double.isNaN(avgAmount)) avgAmount = null;
        offersRepositoryResponse.avgAmount = avgAmount;

        return offersRepositoryResponse;
    }
}
