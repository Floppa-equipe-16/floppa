package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.ProductUtils;

public abstract class IProductRepositoryITest {
    public static final String SELLER_ID = "SELLER";

    private final IProductRepository repository = createRepository();

    private Product product;

    private Product otherProduct;

    @BeforeEach
    public void setUp() {
        product = ProductUtils.createProduct();
        otherProduct = ProductUtils.createProduct2();
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
    }

    @Test
    public void canFindById() {
        repository.save(product);

        Product foundProduct = repository.findById(product.getId());

        assertThat(foundProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(product.getId()));
    }

    @Test
    public void canFindAllWhenFilterIsEmpty() {
        repository.save(product);
        repository.save(otherProduct);

        List<Product> products = repository.findAll(ProductUtils.createEmptyFilter());

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllReturnsEmptyWhenNoProduct() {
        List<Product> products = repository.findAll(ProductUtils.createEmptyFilter());

        assertThat(products).isEmpty();
    }

    @Test
    public void findAllReturnsOnlyMatchingSellerIDProduct() {
        repository.save(product);
        repository.save(otherProduct);
        ProductFilter filter = createProductFilterWithPriceRange();

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findAllReturnsOnlyPriceInRangeProduct() {
        repository.save(product);
        repository.save(otherProduct);
        ProductFilter filter = createProductFilterWithPriceRange();

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void canFindAllBySellerId() {
        repository.save(product);
        repository.save(otherProduct);

        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllBySellerIdReturnsEmptyListWhenNoProduct() {
        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products).isEmpty();
    }

    @Test
    public void canSaveWhenProductAlreadyExists() {
        repository.save(product);
        repository.save(product);

        Product foundProduct = repository.findById(product.getId());
        assertThat(foundProduct.getId()).isEqualTo(product.getId());
    }

    private ProductFilter createProductFilterWithPriceRange() {
        return new ProductFilter(SELLER_ID, null, null, 50d, Double.MAX_VALUE);
    }

    protected abstract IProductRepository createRepository();
}
