package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class IProductRepositoryITest {
    public static final String SELLER_ID = "2a74sfs3d2g48";

    private final IProductRepository repository = createRepository();

    private Product productStub;

    private Product otherProductStub;

    @BeforeEach
    public void setUp() {
        productStub = ProductTestUtils.createProduct();
        otherProductStub = ProductTestUtils.createSecondProduct();
    }

    @AfterEach
    public void tearDown() {
        repository.reset();
    }

    @Test
    public void canFindById() {
        repository.save(productStub);

        Product foundProduct = repository.findById(productStub.getId());

        assertThat(foundProduct.getId()).isEqualTo(productStub.getId());
    }

    @Test
    public void findByIdThrowsWhenIdIsAbsent() {
        assertThrows(NotFoundException.class, () -> repository.findById(productStub.getId()));
    }

    @Test
    public void canFindAllWhenFilterIsEmpty() {
        repository.save(productStub);
        repository.save(otherProductStub);

        List<Product> products = repository.findAll(ProductTestUtils.createEmptyFilter());

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllReturnsEmptyWhenNoProduct() {
        List<Product> products = repository.findAll(ProductTestUtils.createEmptyFilter());

        assertThat(products).isEmpty();
    }

    @Test
    public void findAllReturnsOnlyMatchingSellerIDProduct() {
        repository.save(productStub);
        repository.save(otherProductStub);

        ProductFilter filter = createProductFilterWithPriceRange();

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findAllReturnsOnlyPriceInRangeProduct() {
        repository.save(productStub);
        repository.save(otherProductStub);

        ProductFilter filter = createProductFilterWithPriceRange();

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void canFindAllBySellerId() {
        repository.save(productStub);
        repository.save(otherProductStub);

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
        repository.save(productStub);

        repository.save(productStub);

        Product foundProduct = repository.findById(productStub.getId());
        assertThat(foundProduct.getId()).isEqualTo(productStub.getId());
    }

    private ProductFilter createProductFilterWithPriceRange() {
        return new ProductFilter(SELLER_ID, null, null, 50d, Double.MAX_VALUE);
    }

    protected abstract IProductRepository createRepository();
}
