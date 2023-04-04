package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.*;
import ulaval.glo2003.utils.OfferUtils;

public abstract class IOfferRepositoryITest {

    private static final String PRODUCT_ID = "PRODUCT";

    private final IOfferRepository repository = createRepository();

    private Offer offer;

    @BeforeEach
    public void setUp() {
        offer = OfferUtils.createOffer();
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
    }

    @Test
    public void canFindById() {
        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());

        assertThat(foundOffer.getId()).isEqualTo(offer.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(offer.getId()));
    }

    @Test
    public void canFindAllByProductId() {
        Offer otherOfferStub = OfferUtils.createSecondOffer();
        repository.save(offer);
        repository.save(otherOfferStub);

        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers.size()).isEqualTo(2);
    }

    @Test
    public void findAllByProductIdReturnEmptyListWhenNoOffers() {
        List<Offer> offers = repository.findAllByProductId(PRODUCT_ID);

        assertThat(offers).isEmpty();
    }

    @Test
    public void canSaveWhenOfferAlreadyExists() {
        repository.save(offer);
        repository.save(offer);

        Offer foundOffer = repository.findById(offer.getId());

        assertThat(foundOffer.getId()).isEqualTo(offer.getId());
    }

    protected abstract IOfferRepository createRepository();
}
