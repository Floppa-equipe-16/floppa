package ulaval.glo2003.api.product;

import ulaval.glo2003.domain.exceptions.MissingParamException;

public class ProductRequest {
    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;

    public void validateProductNonNullParameter() {
        if (title == null) throw new MissingParamException("title");
        if (description == null) throw new MissingParamException("description");
        if (suggestedPrice == null) throw new MissingParamException("suggestedPrice");
        if (category == null) throw new MissingParamException("category");
    }
}
