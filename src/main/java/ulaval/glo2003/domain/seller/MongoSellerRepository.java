package ulaval.glo2003.domain.seller;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.ws.rs.NotFoundException;
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
            return new Seller(
                    mongoSeller.id,
                    mongoSeller.name,
                    mongoSeller.birthdate,
                    mongoSeller.email,
                    mongoSeller.phoneNumber,
                    mongoSeller.bio,
                    mongoSeller.createdAt);
        } else {
            throw new NotFoundException(String.format("Seller with id '%s' not found", id));
        }
    }

    @Override
    public void save(Seller seller) {
        MongoSeller mongoSeller = new MongoSeller();
        mongoSeller.id = seller.getId();
        mongoSeller.name = seller.getName();
        mongoSeller.birthdate = seller.getBirthdate();
        mongoSeller.email = seller.getEmail();
        mongoSeller.phoneNumber = seller.getPhoneNumber();
        mongoSeller.bio = seller.getBio();
        mongoSeller.createdAt = seller.getCreatedAt();

        datastore.save(mongoSeller);
    }

    @Override
    public void reset() {
        datastore.getDatabase().drop();
    }
}
