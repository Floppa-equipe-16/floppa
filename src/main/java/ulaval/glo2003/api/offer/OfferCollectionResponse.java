package ulaval.glo2003.api.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferCollectionResponse {

    public Integer count;
    public Double avgAmount;
    public Double minAmount;
    public Double maxAmount;

    public List<OfferResponse> items;

    public OfferCollectionResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferCollectionResponse) {
            OfferCollectionResponse response = ((OfferCollectionResponse) o);
            return isEqualsTo(response);
        } else return false;
    }

    private boolean isEqualsTo(OfferCollectionResponse response) {
        return Objects.equals(count, response.count)
                && Objects.equals(avgAmount, response.avgAmount)
                && Objects.equals(minAmount, response.minAmount)
                && Objects.equals(maxAmount, response.maxAmount)
                && Objects.equals(items, response.items);
    }
}
