package ulaval.glo2003.api.product;

import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.product.Product;

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

    private boolean isProductEquals(Product product) {
        return title.equals(product.getTitle())
                && description.equals(product.getDescription())
                && suggestedPrice.equals(product.getSuggestedPrice())
                && category.equals(product.getCategory());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductRequest) {
            ProductRequest productRequest = ((ProductRequest) o);
            return isProductRequestEquals(productRequest);
        } else if (o instanceof Product) {
            Product product = ((Product) o);
            return isProductEquals(product);
        } else return false;
    }
}
