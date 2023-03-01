package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOfferRepositoryTest {
    private static final String PRODUCT_ID = "PRODUCT";
    public static final String USERNAME = "Alice";
    public static final double AMOUNT = 100d;
    public static final String MESSAGE =
            "one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen";

    private InMemoryOfferRepository repository;
    private Offer offer;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryOfferRepository();
        offer = createOffer();
    }

    @Test
    public void canFindById() {
        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());

        assertThat(foundOffer).isEqualTo(offer);
    }

    @Test
    public  void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(offer.getId()));
    }

    @Test
    public void canFindAllByProductId() {
        repository.save(offer);
        repository.save(createOffer());

        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers.size()).isEqualTo(2);
    }

    @Test
    public void findAllByProductIdReturnEmptyListWhenNoOffers() {
        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers).isEmpty();
    }

    @Test
    public  void canSaveWhenOfferAlreadyExists() {
        repository.save(offer);

        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());
        assertThat(foundOffer).isEqualTo(offer);
    }

    private Offer createOffer() {
        OfferFactory factory = new OfferFactory();
        return factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);
    }
}
