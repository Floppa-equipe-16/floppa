package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemorySellerRepositoryTest {
    public static final String NAME = "Alice";
    public static final String BIRTHDATE = "2000-01-01";
    public static final String EMAIL = "Alice@floppa.com";
    public static final String PHONE_NUMBER = "14181234567";
    public static final String BIO = "My name is Alice!";

    private InMemorySellerRepository repository;
    private Seller seller;

    @BeforeEach
    protected void setUp() {
        repository = new InMemorySellerRepository();
        seller = new Seller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);
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
