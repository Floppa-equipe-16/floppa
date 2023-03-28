package ulaval.glo2003.domain.infrastructure.mongo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.ws.rs.NotFoundException;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MongoSellerRepository implements ISellerRepository {

    private final Datastore datastore;

    public MongoSellerRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Seller findById(String id) {
        Query<MongoSeller> sellersQuery = datastore.find(MongoSeller.class).filter(Filters.eq("id", id));
        List<MongoSeller> sellers =
                StreamSupport.stream(sellersQuery.spliterator(), true).collect(Collectors.toList());

        if (sellers.size() == 1) {
            MongoSeller mongoSeller = sellers.get(0);
            return deserialize(mongoSeller);
        } else {
            throw new NotFoundException(String.format("Seller with id '%s' not found", id));
        }
    }

    @Override
    public void save(Seller seller) {
        datastore.save(serialize(seller));
    }

    @Override
    public void reset() {
        datastore.getDatabase().drop();
    }

    private MongoSeller serialize(Seller seller) {
        MongoSeller mongoSeller = new MongoSeller();
        mongoSeller.id = seller.getId();
        mongoSeller.name = seller.getName();
        mongoSeller.birthdate = seller.getBirthdate();
        mongoSeller.email = seller.getEmail();
        mongoSeller.phoneNumber = seller.getPhoneNumber();
        mongoSeller.bio = seller.getBio();
        mongoSeller.createdAt = seller.getCreatedAt();
        return mongoSeller;
    }

    private Seller deserialize(MongoSeller mongoSeller) {
        return new Seller(
                mongoSeller.id,
                mongoSeller.name,
                mongoSeller.createdAt,
                mongoSeller.birthdate,
                mongoSeller.email,
                mongoSeller.phoneNumber,
                mongoSeller.bio);
    }
}
