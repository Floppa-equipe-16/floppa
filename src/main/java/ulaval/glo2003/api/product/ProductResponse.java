package ulaval.glo2003.api.product;

import ulaval.glo2003.api.offer.OfferCollectionResponse;

public class ProductResponse {

    protected static class SellerInfo {
        public String id;
        public String name;
    }

    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public SellerInfo seller;
    public OfferCollectionResponse offers;

    public void addSellerInfo(String id, String name) {
        seller = new SellerInfo();
        seller.id = id;
        seller.name = name;
    }
}
