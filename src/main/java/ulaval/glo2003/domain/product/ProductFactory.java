package ulaval.glo2003.domain.product;

import java.time.Instant;
import java.util.UUID;

public class ProductFactory {
    private static final double TWO_DECIMAL_ROUNDING_FACTOR = 100d;

    public Product createProduct(
            String sellerId, String title, String description, Double suggestedPrice, String category) {
        String id = UUID.randomUUID().toString();
        String createdAt = Instant.now().toString();
        suggestedPrice = Math.round(suggestedPrice * TWO_DECIMAL_ROUNDING_FACTOR) / TWO_DECIMAL_ROUNDING_FACTOR;

        Product product =
                new Product(id, sellerId, title, createdAt, description, suggestedPrice, SaleStatus.ongoing, category);
        ProductValidator.validateParam(product);

        return product;
    }
}
