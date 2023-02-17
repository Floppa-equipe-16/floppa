package ulaval.glo2003.domain;

import ulaval.glo2003.api.product.ProductRequest;

public class ProductConverter {

    public static Product productRequestToProduct(ProductRequest productRequest) {
        return new Product(
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                productRequest.category);
    }
}
