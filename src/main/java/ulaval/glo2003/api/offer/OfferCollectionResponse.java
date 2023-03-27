package ulaval.glo2003.api.offer;

import java.util.List;

public class OfferCollectionResponse {

    public Integer count;
    public Double avgAmount;
    public Double minAmount;
    public Double maxAmount;

    public List<OfferResponse> items;
}
