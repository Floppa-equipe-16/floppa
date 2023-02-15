package ulaval.glo2003.api.offer;

import java.util.ArrayList;
import ulaval.glo2003.domain.Offer;

public class OffersResponse {

    public Integer count;
    public Double avgAmount;

    public OffersResponse(ArrayList<Offer> offers) {
        count = offers.size();
        avgAmount = offers.stream().mapToDouble(Offer::getAmount).average().orElse(Double.NaN);
        if (Double.isNaN(avgAmount)) avgAmount = null;
    }
}
