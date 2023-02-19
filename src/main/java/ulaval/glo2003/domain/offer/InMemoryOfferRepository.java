package ulaval.glo2003.domain.offer;

import jakarta.ws.rs.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryOfferRepository implements IOfferRepository {
    private final Map<String, Offer> offers;

    public InMemoryOfferRepository() {
        this.offers = new HashMap<>();
    }

    @Override
    public Offer findById(String id) {
        return Optional.ofNullable(offers.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Offer with id '%s' not found", id)));
    }

    @Override
    public List<Offer> findAllByProductId(String id) {
        return offers.values().stream().filter(offer -> offer.getProductId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public void add(Offer offer) {
        offers.put(offer.getId(), offer);
    }
}
