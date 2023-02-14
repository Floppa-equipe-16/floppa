package ulaval.glo2003.api.product;

import ulaval.glo2003.domain.Product;

public class ProductResponse {
    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public ProductResponse(Product product) {
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.suggestedPrice = product.getSuggestedPrice();
        this.category = product.getCategory();
        this.id = product.getId();
        this.createdAt = product.getCreatedAt();
    }
}
