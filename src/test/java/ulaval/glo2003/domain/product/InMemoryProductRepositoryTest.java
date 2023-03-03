package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InMemoryProductRepositoryTest {
    private static final String ID = "1";
    private static final String SECOND_ID = "2";
    private static final String SELLER_ID = "SELLER";

    private InMemoryProductRepository repository;

    @Mock
    private Product productStub = mock(Product.class);

    @BeforeEach
    public void setUp() {
        repository = new InMemoryProductRepository();

        when(productStub.getId()).thenReturn(ID);
        when(productStub.getSellerId()).thenReturn(SELLER_ID);
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
    public void canFindAllBySellerId() {
        Product otherProductStub = mock(Product.class);
        when(otherProductStub.getId()).thenReturn(SECOND_ID);
        when(otherProductStub.getSellerId()).thenReturn(SELLER_ID);
        repository.save(productStub);
        repository.save(otherProductStub);

        List<Product> products = repository.findAllBySellerId(SELLER_ID);

        assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void findAllBySellerIdReturnEmptyListWhenNoProducts() {
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
}
