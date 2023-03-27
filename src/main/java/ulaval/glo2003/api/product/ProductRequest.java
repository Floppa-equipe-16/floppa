package ulaval.glo2003.api.product;

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

    private boolean isProductRequestEquals(ProductRequest request) {
        return title.equals(request.title)
                && description.equals(request.description)
                && suggestedPrice.equals(request.suggestedPrice)
                && category.equals(request.category);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductRequest) {
            ProductRequest productRequest = ((ProductRequest) o);
            return isProductRequestEquals(productRequest);
        } else return false;
    }
}
