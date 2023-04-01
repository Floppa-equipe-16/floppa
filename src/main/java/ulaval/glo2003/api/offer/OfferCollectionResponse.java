package ulaval.glo2003.api.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferCollectionResponse {

    public Integer count;
    public Double avgAmount;
    public Double minAmount;
    public Double maxAmount;

    public List<OfferResponse> items;
}
