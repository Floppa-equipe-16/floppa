package ulaval.glo2003.api.product;

import ulaval.glo2003.api.offer.OffersResponse;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

public class ProductResponse {
    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public SellerResponse seller;
    public OffersResponse offers;

    public ProductResponse(Seller seller, Product product) {
        this(product);
        this.seller = SellerResponse.ctorSimplifiedSellerResponse(seller);
    }

    public ProductResponse(Product product) {
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.suggestedPrice = product.getSuggestedPrice();
        this.category = product.getCategory();
        this.id = product.getId();
        this.createdAt = product.getCreatedAt();
        this.offers = new OffersResponse(product.getOffers());
    }
}
