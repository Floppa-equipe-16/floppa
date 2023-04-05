package ulaval.glo2003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OfferResponse;
import ulaval.glo2003.api.offer.SelectedOfferResponse;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferFactory;

public class OfferMapper {
    private final OfferFactory factory;

    public OfferMapper(OfferFactory factory) {
        this.factory = factory;
    }

    public Offer requestToOffer(String productId, String buyerName, OfferRequest offerRequest) {
        return factory.createOffer(productId, buyerName, offerRequest.amount, offerRequest.message);
    }

    public OfferCollectionResponse offersToSummaryCollectionResponse(List<Offer> offers) {
        OfferCollectionResponse response = new OfferCollectionResponse();

        response.count = offers.size();
        double average = offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
        if (!Double.isNaN(average)) {
            response.avgAmount = average;
        }

        return response;
    }

    public OfferCollectionResponse offersToCompleteCollectionResponse(List<Offer> offers) {
        OfferCollectionResponse response = new OfferCollectionResponse();

        response.count = offers.size();

        if (!offers.isEmpty()) {
            response.avgAmount =
                    offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
            response.minAmount =
                    offers.stream().mapToDouble(Offer::getAmount).min().orElse(Double.NaN);
            response.maxAmount =
                    offers.stream().mapToDouble(Offer::getAmount).max().orElse(Double.NaN);

            response.items = offers.stream().map(OfferResponse::new).collect(Collectors.toList());
        } else {
            response.items = new ArrayList<>();
        }

        return response;
    }

    public SelectedOfferResponse offerToSelectedOfferResponse(Offer offer) {
        return new SelectedOfferResponse(offer);
    }
}
