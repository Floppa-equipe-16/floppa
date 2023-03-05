package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.domain.product.Product;

public class SellerTest {

    private Seller seller;

    @Mock
    Product productStub = mock(Product.class);

    @BeforeEach
    public void setUp() {
        seller = SellerTestUtils.createSeller();

        doReturn("id-test").when(productStub).getId();
    }

    @Test
    public void canCopy() {
        Seller sellerCopy = new Seller(seller);

        assertThat(seller).isEqualTo(sellerCopy);
    }

    @Test
    public void canAddProduct() {
        seller.addProduct(productStub);
        Optional<Product> product = Optional.ofNullable(seller.getProductById(productStub.getId()));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void addProductOnlyAddsOnceWithDuplicate() {
        seller.addProduct(productStub);
        seller.addProduct(productStub);

        assertThat(seller.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void canCompareIdenticalSellers() {
        Seller identicalSeller = SellerTestUtils.createSeller();

        assertThat(seller).isEqualTo(identicalSeller);
    }

    @Test
    public void canCompareDifferentSellers() {
        Seller differentSeller = new Seller(
                "ID", "Gertrude", Instant.MIN.toString(), "1010-10-10", "Gertrude@email.com", "11231231234", "Not");

        assertThat(seller).isNotEqualTo(differentSeller);
    }
}
