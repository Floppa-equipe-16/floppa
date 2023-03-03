package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InMemoryOfferRepositoryTest {
    public static final String ID = "1";
    private static final String PRODUCT_ID = "PRODUCT";

    private InMemoryOfferRepository repository;

    @Mock
    private Offer offerStub = mock(Offer.class);

    @BeforeEach
    public void setUp() {
        repository = new InMemoryOfferRepository();

        when(offerStub.getId()).thenReturn(ID);
        when(offerStub.getProductId()).thenReturn(PRODUCT_ID);
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
        Offer otherOfferStub = mock(Offer.class);
        when(otherOfferStub.getId()).thenReturn("123");
        when(otherOfferStub.getProductId()).thenReturn(PRODUCT_ID);
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
}
