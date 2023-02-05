package ulaval.glo2003.api;

public class ProductResponse {

    public String title;

    public String description;

    public Number suggestedPrice;

    public String category;

    public String id;

    public String createdAt;

    public ProductResponse(String title,
                           String description,
                           Number suggestedPrice,
                           String category,
                           String id,
                           String createdAt) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.category = category;
        this.id = id;
        this.createdAt = createdAt;
    }
}
