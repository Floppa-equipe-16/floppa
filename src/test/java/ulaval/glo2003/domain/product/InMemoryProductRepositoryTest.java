package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryProductRepositoryTest {
    private static final String ID = "1";
    private static final String SECOND_ID = "2";
    private static final String SELLER_ID = "SELLER";
    private static final String TITLE = "Title";
    private static final String CREATION_DATE = Instant.MAX.toString();
    private static final String DESCRIPTION = "Complete description";
    private static final double SUGGESTED_PRICE = 10d;
    private static final String CATEGORY = ProductCategory.other.toString();
    private InMemoryProductRepository repository;
    private Product product;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryProductRepository();
        product = createProduct(ID);
    }

    @Test
    public void canFindById() {
        repository.save(product);

        Product foundProduct = repository.findById(product.getId());

        assertThat(foundProduct).isEqualTo(product);
    }

    @Test
    public  void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(product.getId()));
    }

    @Test
    public void canFindAllBySellerId() {
        repository.save(product);
        repository.save(createProduct(SECOND_ID));

        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllBySellerIdReturnEmptyListWhenNoProducts() {
        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products).isEmpty();
    }

    @Test
    public  void canSaveWhenProductAlreadyExists() {
        repository.save(product);

        repository.save(product);

        Product foundProduct = repository.findById(product.getId());
        assertThat(foundProduct).isEqualTo(product);
    }

    private Product createProduct(String id) {
        return new Product(id, SELLER_ID, TITLE, CREATION_DATE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
    }
}
