package ulaval.glo2003.api.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ulaval.glo2003.domain.Offer;

public class OffersResponse {
    public Integer count;
    public Double avgAmount;
    public Double minAmount;
    public Double maxAmount;
    public List<OfferResponse> offers;

    public OffersResponse(ArrayList<Offer> offers) {
        count = offers.size();

        if(!offers.isEmpty()) {
            avgAmount = offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
            minAmount = offers.stream().mapToDouble(Offer::getAmount).min().orElse(Double.NaN);
            maxAmount = offers.stream().mapToDouble(Offer::getAmount).max().orElse(Double.NaN);

            this.offers = offers.stream().map(OfferResponse::new).collect(Collectors.toList());
        } else {
            this.offers = new ArrayList<>();
        }
    }
}
