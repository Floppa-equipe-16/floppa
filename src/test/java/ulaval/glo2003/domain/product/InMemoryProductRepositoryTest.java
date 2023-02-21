package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryProductRepositoryTest {
    private static final String SELLER_ID = "SELLER";
    public static final String TITLE = "Title";
    public static final String DESCRIPTION = "Complete description";
    public static final double SUGGESTED_PRICE = 10d;
    public static final String CATEGORY = ProductCategory.other.toString();
    private InMemoryProductRepository repository;
    private Product product;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRepository();
        product = createProduct();
    }

    @Test
    void canFindById() {
        repository.save(product);

        Product foundProduct = repository.findById(product.getId());

        assertThat(foundProduct).isEqualTo(product);
    }

    @Test
    protected void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(product.getId()));
    }

    @Test
    void canFindAllBySellerId() {
        repository.save(product);
        repository.save(createProduct());

        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findAllBySellerIdReturnEmptyListWhenNoProducts() {
        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products).isEmpty();
    }

    @Test
    protected void canSaveWhenProductAlreadyExists() {
        repository.save(product);

        repository.save(product);

        Product foundProduct = repository.findById(product.getId());
        assertThat(foundProduct).isEqualTo(product);
    }

    private Product createProduct() {
        return new Product(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
    }
}
