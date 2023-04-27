package ulaval.glo2003.domain.infrastructure.inMemory;

import jakarta.ws.rs.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;

public class InMemorySellerRepository implements ISellerRepository {
    private final Map<String, Seller> sellers;

    public InMemorySellerRepository() {
        sellers = new HashMap<>();
    }

    @Override
    public Seller findById(String id) {
        Seller seller = Optional.ofNullable(sellers.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", id)));

        return new Seller(seller);
    }

    @Override
    public List<Seller> findTopRanked(Integer amount) {
        return sellers.values().stream()
                .sorted(Comparator.comparingDouble(Seller::getScore).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Seller seller) {
        sellers.put(seller.getId(), seller);
    }

    @Override
    public void reset() {
        sellers.clear();
    }
}
