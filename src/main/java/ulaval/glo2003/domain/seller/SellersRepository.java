package ulaval.glo2003.domain.seller;

import jakarta.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SellersRepository {

    private final Map<String, Seller> sellersMap;

    public SellersRepository() {
        sellersMap = new HashMap<>();
    }

    public void addSeller(Seller seller) {
        sellersMap.put(seller.getId(), seller);
    }

    public Seller findSellerBySellerId(String sellerId) {
        return Optional.ofNullable(sellersMap.get(sellerId))
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", sellerId)));
    }

    public Seller findSellerByProductId(String productId) {
        return sellersMap.values().stream()
                .filter(seller -> seller.getProductById(productId) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", productId)));
    }
}
