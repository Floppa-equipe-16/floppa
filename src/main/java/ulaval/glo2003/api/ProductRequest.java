package ulaval.glo2003.api;

import ulaval.glo2003.api.exceptionHandling.ProductMissingParamException;
import ulaval.glo2003.api.exceptionHandling.SellerMissingParamException;

public class ProductRequest {

    public String title;
    public String description;
    public Number suggestedPrice;
    public String category;

    public void validateProductNonNullParameter() {
        if (title == null)
            throw new ProductMissingParamException("Missing title value");
        if (description == null)
            throw new ProductMissingParamException("Missing descritpion value");
        if (suggestedPrice == null)
            throw new ProductMissingParamException("Missing suggestedPrice number");
        if (category == null)
            throw new ProductMissingParamException("Missing category value");
    }
}
