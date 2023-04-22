package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.SellerTestUtils;

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
    public void canFindSomeWhenLessOrEqThanTopSize() {
        repository.save(seller);
        repository.save(SellerTestUtils.createSeller2());

        List<Seller> foundSellers = repository.findSome(2);

        assertThat(foundSellers.size()).isEqualTo(2);
    }

    @Test
    public void canFindSomeWhenMoreThanTopSize() {
        repository.save(seller);
        repository.save(SellerTestUtils.createSeller2());

        List<Seller> foundSellers = repository.findSome(1);

        assertThat(foundSellers.size()).isEqualTo(1);
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
