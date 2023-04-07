package ulaval.glo2003.api.product;

import java.util.Objects;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class ProductRequest {
    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;

    public void validate() {
        if (title == null) throw new MissingParamException("title");
        if (description == null) throw new MissingParamException("description");
        if (suggestedPrice == null) throw new MissingParamException("suggestedPrice");
        if (category == null) throw new MissingParamException("category");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductRequest) {
            ProductRequest productRequest = ((ProductRequest) o);
            return isEqualsTo(productRequest);
        } else return false;
    }

    private boolean isEqualsTo(ProductRequest request) {
        return Objects.equals(title, request.title)
                && Objects.equals(description, request.description)
                && Objects.equals(suggestedPrice, request.suggestedPrice)
                && Objects.equals(category, request.category);
    }
}
