package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOfferRepositoryTest {
    private static final String PRODUCT_ID = "PRODUCT";
    private InMemoryOfferRepository repository;
    private Offer offer;

    @BeforeEach
    void setUp() {
        repository = new InMemoryOfferRepository();
        offer = createOffer();
    }

    @Test
    void canFindById() {
        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());

        assertThat(foundOffer).isEqualTo(offer);
    }

    @Test
    protected void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(offer.getId()));
    }

    @Test
    void findAllByProductId() {
        repository.save(offer);
        repository.save(createOffer());

        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers.size()).isEqualTo(2);
    }

    @Test
    void findAllByProductIdReturnEmptyListWhenNoOffers() {
        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers.isEmpty()).isTrue();
    }

    @Test
    protected void canSaveWhenOfferAlreadyExists() {
        repository.save(offer);

        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());
        assertThat(foundOffer).isEqualTo(offer);
    }

    private Offer createOffer() {
        return new Offer(
                PRODUCT_ID,
                "Alice",
                100d,
                "one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen");
    }
}
