package ulaval.glo2003.infrastructure.inMemory;

import jakarta.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.Offer;

public class InMemoryOfferRepository implements IOfferRepository {
    private final Map<String, Offer> offers;

    public InMemoryOfferRepository() {
        this.offers = new HashMap<>();
    }

    @Override
    public Offer findById(String id) {
        Offer offer = Optional.ofNullable(offers.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Offer with id '%s' not found", id)));

        return new Offer(offer);
    }

    @Override
    public List<Offer> findAllByProductId(String id) {
        return offers.values().stream()
                .filter(offer -> offer.getProductId().equals(id))
                .map(Offer::new)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Offer offer) {
        offers.put(offer.getId(), offer);
    }

    @Override
    public void reset() {
        offers.clear();
    }
}
