package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemorySellerRepositoryTest {
    private InMemorySellerRepository repository;
    private Seller seller;

    @BeforeEach
    public void setUp() {
        repository = new InMemorySellerRepository();
        seller = SellerTestUtils.createSeller();
    }

    @Test
    public void canFindById() {
        repository.save(seller);

        Seller foundSeller = repository.findById(seller.getId());

        assertThat(foundSeller).isEqualTo(seller);
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
        assertThat(foundSeller).isEqualTo(seller);
    }
}
