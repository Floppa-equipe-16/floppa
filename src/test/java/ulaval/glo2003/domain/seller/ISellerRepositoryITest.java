package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ISellerRepositoryITest {

    private final ISellerRepository repository = createRepository();

    private Seller seller;

    @BeforeEach
    public void setUp() {
        seller = SellerTestUtils.createSeller();
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
    }

    @Test
    public void canFindById() {
        repository.save(seller);

        Seller foundSeller = repository.findById(seller.getId());

        assertThat(foundSeller.getId()).isEqualTo(seller.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(seller.getId()));
    }

    @Test
    public void canSaveWhenSellerAlreadyExists() {
        repository.save(seller);
        repository.save(seller);

        Seller foundSeller = repository.findById(seller.getId());
        assertThat(foundSeller.getId()).isEqualTo(seller.getId());
    }

    protected abstract ISellerRepository createRepository();
}
