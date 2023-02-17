package ulaval.glo2003.api.product;

import ulaval.glo2003.api.offer.OffersResponse;
import ulaval.glo2003.api.seller.SellerResponse;

public class ProductResponse {
    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public SellerResponse seller;
    public OffersResponse offers;
}
