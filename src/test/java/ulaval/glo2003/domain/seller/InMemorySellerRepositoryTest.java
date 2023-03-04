package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InMemorySellerRepositoryTest {
    private static final String ID = "1";

    private InMemorySellerRepository repository;

    @Mock
    private Seller sellerStub = mock(Seller.class);

    @BeforeEach
    public void setUp() {
        repository = new InMemorySellerRepository();

        when(sellerStub.getId()).thenReturn(ID);
    }

    @Test
    public void canFindById() {
        repository.save(sellerStub);

        Seller foundSeller = repository.findById(sellerStub.getId());

        assertThat(foundSeller.getId()).isEqualTo(sellerStub.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(sellerStub.getId()));
    }

    @Test
    public void canSaveWhenSellerAlreadyExists() {
        repository.save(sellerStub);

        repository.save(sellerStub);

        Seller foundSeller = repository.findById(sellerStub.getId());
        assertThat(foundSeller.getId()).isEqualTo(sellerStub.getId());
    }
}
