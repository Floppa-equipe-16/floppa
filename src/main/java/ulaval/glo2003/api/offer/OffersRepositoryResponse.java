package ulaval.glo2003.api.offer;

import java.util.List;

public class OffersRepositoryResponse {

    public Integer count;
    public Double avgAmount;
    public Double minAmount;
    public Double maxAmount;

    public List<OfferResponse> offers;
}
