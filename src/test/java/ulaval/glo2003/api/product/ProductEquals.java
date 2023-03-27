package ulaval.glo2003.api.product;

import ulaval.glo2003.domain.product.Product;

public class ProductEquals {
    public static boolean productRequestEqualsProduct(ProductRequest request, Product product) {
        return request.title.equals(product.getTitle())
                && request.description.equals(product.getDescription())
                && request.suggestedPrice.equals(product.getSuggestedPrice())
                && request.category.equals(product.getCategory());
    }

    public static boolean productResponseEqualsProduct(ProductResponse response, Product product) {
        return response.title.equals(product.getTitle())
                && response.description.equals(product.getDescription())
                && response.suggestedPrice.equals(product.getSuggestedPrice())
                && response.category.equals(product.getCategory())
                && response.id.equals(product.getId())
                && response.createdAt.equals(product.getCreatedAt());
    }
}


