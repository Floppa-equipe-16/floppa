package ulaval.glo2003.domain.seller;

import jakarta.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemorySellerRepository implements ISellerRepository {
    private final Map<String, Seller> sellers;

    public InMemorySellerRepository() {
        sellers = new HashMap<>();
    }

    @Override
    public Seller findById(String id) {
        return Optional.ofNullable(sellers.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", id)));
    }

    @Override
    public void save(Seller seller) {
        sellers.put(seller.getId(), seller);
    }
}
