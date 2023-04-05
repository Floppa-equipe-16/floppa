package ulaval.glo2003.api.product;

import java.util.List;
import java.util.Objects;

public class ProductCollectionResponse {
    public List<ProductResponse> products;

    public ProductCollectionResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductCollectionResponse) {
            ProductCollectionResponse response = ((ProductCollectionResponse) o);
            return isEqualsTo(response);
        } else return false;
    }

    private boolean isEqualsTo(ProductCollectionResponse response) {

        return Objects.equals(products, response.products);
    }
}
