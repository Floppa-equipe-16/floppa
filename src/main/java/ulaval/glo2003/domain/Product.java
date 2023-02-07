package ulaval.glo2003.domain;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.UUID;
import ulaval.glo2003.api.ProductCategory;
import ulaval.glo2003.api.exceptionHandling.InvalidParamException;

public class Product {
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final String category;
    private final String id;
    private final String createdAt;

    public Product(String title, String description, Double suggestedPrice, String category) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = Double.parseDouble((new DecimalFormat("#.##")).format(suggestedPrice));
        this.category = category;

        validateProductParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    private void validateProductParameters() {
        if (isStringEmpty(title)) throw new InvalidParamException("title");
        if (isStringEmpty(description)) throw new InvalidParamException("description");
        if (!doesCategoryExist(category)) throw new InvalidParamException("category");
        if (isSuggestedPriceUnder1()) throw new InvalidParamException("suggestedPrice");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    private boolean isStringEmpty(String s) {
        return s.trim().isEmpty();
    }

    private boolean isSuggestedPriceUnder1() {
        return suggestedPrice.intValue() < 1;
    }

    private boolean doesCategoryExist(String category) {
        try {
            ProductCategory.valueOf(category);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
