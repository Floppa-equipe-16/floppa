package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public abstract class ISellerRepositoryTest {
    private static final String ID = "1";

    private final ISellerRepository repository = createRepository();

    @Mock
    private Seller sellerStub = mock(Seller.class);

    @BeforeEach
    public void setUp() {
        repository.reset();

        when(sellerStub.getId()).thenReturn(ID);
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
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

    protected abstract ISellerRepository createRepository();
}
