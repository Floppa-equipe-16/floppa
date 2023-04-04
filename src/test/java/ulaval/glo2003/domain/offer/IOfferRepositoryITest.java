package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.*;

public abstract class IOfferRepositoryITest {

    private static final String PRODUCT_ID = "PRODUCT";

    private final IOfferRepository repository = createRepository();

    private Offer offerStub;

    @BeforeEach
    public void setUp() {
        offerStub = OfferTestUtils.createOffer();
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
    }

    @Test
    public void canFindById() {
        repository.save(offerStub);

        Offer foundOffer = repository.findById(offerStub.getId());

        assertThat(foundOffer.getId()).isEqualTo(offerStub.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(offerStub.getId()));
    }

    @Test
    public void canFindAllByProductId() {
        Offer otherOfferStub = OfferTestUtils.createSecondOffer();
        repository.save(offerStub);
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
        repository.save(offerStub);
        repository.save(offerStub);

        Offer foundOffer = repository.findById(offerStub.getId());

        assertThat(foundOffer.getId()).isEqualTo(offerStub.getId());
    }

    protected abstract IOfferRepository createRepository();
}
