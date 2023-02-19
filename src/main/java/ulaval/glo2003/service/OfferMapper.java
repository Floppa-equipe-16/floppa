package ulaval.glo2003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.offer.OfferResponse;
import ulaval.glo2003.domain.offer.Offer;

public class OfferMapper {

    public static Offer requestToOffer(String productId, String buyerName, OfferRequest offerRequest) {
        return new Offer(productId, buyerName, offerRequest.amount, offerRequest.message);
    }

    public static OfferCollectionResponse offersToSummaryCollectionResponse(List<Offer> offers) {
        OfferCollectionResponse response = new OfferCollectionResponse();

        response.count = offers.size();
        double average = offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
        if (!Double.isNaN(average)) {
            response.avgAmount = average;
        }

        return response;
    }

    public static OfferCollectionResponse offersToCompleteCollectionResponse(List<Offer> offers) {
        OfferCollectionResponse response = new OfferCollectionResponse();

        response.count = offers.size();

        if (!offers.isEmpty()) {
            response.avgAmount =
                    offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
            response.minAmount =
                    offers.stream().mapToDouble(Offer::getAmount).min().orElse(Double.NaN);
            response.maxAmount =
                    offers.stream().mapToDouble(Offer::getAmount).max().orElse(Double.NaN);

            response.offers = offers.stream().map(OfferResponse::new).collect(Collectors.toList());
        } else {
            response.offers = new ArrayList<>();
        }

        return response;
    }
}
