package ulaval.glo2003.domain.product;

import java.util.Objects;

public class ProductFilter {
    private final String sellerId;
    private final String title;
    private final String category;
    private final Double minPrice;
    private final Double maxPrice;

    public ProductFilter(String sellerId, String title, String category, Double minPrice, Double maxPrice) {
        this.sellerId = Objects.requireNonNullElse(sellerId, "");
        this.title = Objects.requireNonNullElse(title, "");
        this.category = Objects.requireNonNullElse(category, "");
        this.minPrice = Objects.requireNonNullElse(minPrice, 0.0);
        this.maxPrice = Objects.requireNonNullElse(maxPrice, 30000000000.0);
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }
}
