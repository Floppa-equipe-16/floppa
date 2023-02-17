package ulaval.glo2003.domain.seller;

import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;

public class SellersDatabase {

    private final ArrayList<Seller> sellers;

    public SellersDatabase() {
        sellers = new ArrayList<>();
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public Seller findSellerBySellerId(String sellerId) {
        return sellers.stream()
                .filter(seller -> seller.getId().equals(sellerId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", sellerId)));
    }

    public Seller findSellerByProductId(String productId) {
        return sellers.stream()
                .filter(seller -> seller.getProductById(productId) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", productId)));
    }
}
