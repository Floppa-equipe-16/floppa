package ulaval.glo2003.api.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.domain.Offer;

public class OffersResponseFactory {
    public static OffersResponse createSummaryResponse(List<Offer> offers) {
        OffersResponse response = new OffersResponse();
        response.count = offers.size();
        double average = offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);

        if (!Double.isNaN(average)) {
            response.avgAmount = average;
        }

        return response;
    }

    public static OffersResponse createCompleteResponse(List<Offer> offers) {
        OffersResponse response = new OffersResponse();

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