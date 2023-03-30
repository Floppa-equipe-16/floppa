package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public abstract class IProductRepositoryITest {

    private static final String ID = "1";
    private static final String SECOND_ID = "2";
    private static final String SELLER_ID = "SELLER";

    private final IProductRepository repository = createRepository();

    @Mock
    private Product productStub;

    @BeforeEach
    public void setUp() {
        productStub = createProductStub(ID, SELLER_ID);
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
        Product otherProductStub = createProductStub(SECOND_ID, SELLER_ID);
        repository.save(productStub);
        repository.save(otherProductStub);

        List<Product> products = repository.findAll(createEmptyProductFilterStub());

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllReturnsEmptyWhenNoProduct() {
        List<Product> products = repository.findAll(createEmptyProductFilterStub());

        assertThat(products).isEmpty();
    }

    @Test
    public void findAllReturnsOnlyMatchingSellerIDProduct() {
        Product otherProductStub = createProductStub(SECOND_ID, "WRONG");
        repository.save(productStub);
        repository.save(otherProductStub);

        ProductFilter filter = createEmptyProductFilterStub();
        when(filter.getSellerId()).thenReturn(SELLER_ID);

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void findAllReturnsOnlyPriceInRangeProduct() {
        Product otherProductStub = createProductStub(SECOND_ID, SELLER_ID);
        when(otherProductStub.getSuggestedPrice()).thenReturn(10000d);
        repository.save(productStub);
        repository.save(otherProductStub);

        ProductFilter filter = createEmptyProductFilterStub();
        when(filter.getMinPrice()).thenReturn(1d);
        when(filter.getMaxPrice()).thenReturn(100d);

        List<Product> products = repository.findAll(filter);

        assertThat(products.size()).isEqualTo(1);
    }

    private ProductFilter createEmptyProductFilterStub() {
        ProductFilter productFilter = mock(ProductFilter.class);
        when(productFilter.getSellerId()).thenReturn("");
        when(productFilter.getTitle()).thenReturn("");
        when(productFilter.getCategory()).thenReturn("");
        when(productFilter.getMinPrice()).thenReturn(0d);
        when(productFilter.getMaxPrice()).thenReturn(Double.MAX_VALUE);
        return productFilter;
    }

    @Test
    public void canFindAllBySellerId() {
        Product otherProductStub = createProductStub(SECOND_ID, SELLER_ID);
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

    private Product createProductStub(String id, String sellerId) {
        Product stub = mock(Product.class);
        when(stub.getId()).thenReturn(id);
        when(stub.getSellerId()).thenReturn(sellerId);
        when(stub.getTitle()).thenReturn("title");
        when(stub.getSuggestedPrice()).thenReturn(10d);
        when(stub.getSaleStatus()).thenReturn(SaleStatus.ongoing);
        when(stub.getCategory()).thenReturn(ProductCategory.other.toString());
        return stub;
    }

    protected abstract IProductRepository createRepository();
}
