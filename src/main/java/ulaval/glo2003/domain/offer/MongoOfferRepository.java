package ulaval.glo2003.domain.offer;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MongoOfferRepository implements IOfferRepository {

    private final Datastore datastore;

    public MongoOfferRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Offer findById(String id) {
        Query<MongoOffer> offersQuery = datastore.find(MongoOffer.class).filter(Filters.eq("id", id));
        List<MongoOffer> offers =
                StreamSupport.stream(offersQuery.spliterator(), true).collect(Collectors.toList());

        if (offers.size() == 1) {
            MongoOffer mongoOffer = offers.get(0);
            return mongoToDomain(mongoOffer);
        } else {
            throw new NotFoundException(String.format("Offer with id '%s' not found", id));
        }
    }

    @Override
    public List<Offer> findAllByProductId(String id) {
        Query<MongoOffer> offersQuery = datastore.find(MongoOffer.class).filter(Filters.eq("productId", id));
        return StreamSupport.stream(offersQuery.spliterator(), true)
                .map(this::mongoToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Offer offer) {
        datastore.save(domainToMongo(offer));
    }

    @Override
    public void reset() {
        datastore.getDatabase().drop();
    }

    private MongoOffer domainToMongo(Offer offer) {
        MongoOffer mongoOffer = new MongoOffer();
        mongoOffer.id = offer.getId();
        mongoOffer.productId = offer.getProductId();
        mongoOffer.username = offer.getUsername();
        mongoOffer.amount = offer.getAmount();
        mongoOffer.message = offer.getMessage();
        mongoOffer.createdAt = offer.getCreatedAt();
        return mongoOffer;
    }

    private Offer mongoToDomain(MongoOffer mongoOffer) {
        return new Offer(
                mongoOffer.id,
                mongoOffer.productId,
                mongoOffer.username,
                mongoOffer.amount,
                mongoOffer.message,
                mongoOffer.createdAt);
    }
}
