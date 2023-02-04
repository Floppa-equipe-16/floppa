package ulaval.glo2003.domain;

import ulaval.glo2003.api.ProductCategory;
import ulaval.glo2003.api.exceptionHandling.ProductInvalidParamException;
import ulaval.glo2003.api.exceptionHandling.SellerInvalidParamException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class Product {
    private final String title;
    private final String description;
    private final Number suggestedPrice;
    private final String category;

    private final String id;
    private final String createdAt;

    public Product(String title, String description, Number suggestedPrice, String category) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.category = category;

        validateProductParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    private void validateProductParameters() {
        if (isStringEmpty(title))
            throw new ProductInvalidParamException("Invalid title value");
        if (isStringEmpty(description))
            throw new ProductInvalidParamException("Invalid description value");
        if (isCategoryExist(category))
            throw new ProductInvalidParamException("Invalid category value");
        if (isSuggestedPriceUnder1())
            throw new ProductInvalidParamException("Invalid suggestedPrice number");
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Number getSuggestedPrice() {
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

    private boolean isCategoryExist(String categorie){
        try {
            ProductCategory.valueOf(categorie);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
