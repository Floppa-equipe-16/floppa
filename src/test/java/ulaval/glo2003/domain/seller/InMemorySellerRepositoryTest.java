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
    protected void setUp() {
        repository = new InMemorySellerRepository();
        seller = new Seller("Alice", "2000-01-01", "Alice@floppa.com", "14181234567", "My name is Alice!");
    }

    @Test
    protected void canFindById() {
        repository.save(seller);

        Seller foundSeller = repository.findById(seller.getId());

        assertThat(foundSeller).isEqualTo(seller);
    }

    @Test
    protected void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(seller.getId()));
    }

    @Test
    protected void canSaveWhenSellerAlreadyExists() {
        repository.save(seller);

        repository.save(seller);

        Seller foundSeller = repository.findById(seller.getId());
        assertThat(foundSeller).isEqualTo(seller);
    }
}
