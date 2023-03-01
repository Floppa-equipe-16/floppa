package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.domain.product.Product;

public class SellerTest {

    private Seller seller;

    @Mock
    Product productMock = mock(Product.class);

    @Mock
    Seller sellerMock = mock(Seller.class);

    @BeforeEach
    public void setUp() {
        seller = SellerTestUtils.createSeller();

        doReturn("id-test").when(productMock).getId();
    }

    @Test
    public void canCopy() {
        Seller sellerCopy = new Seller(seller);

        assertThat(seller).isEqualTo(sellerCopy);
    }

    @Test
    public void canAddProduct() {
        seller.addProduct(productMock);
        Optional<Product> product = Optional.ofNullable(seller.getProductById(productMock.getId()));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void addProductOnlyAddsOnceWithDuplicate() {
        seller.addProduct(productMock);
        seller.addProduct(productMock);

        assertThat(seller.getProducts().size()).isEqualTo(1);
    }

    @Test
    public void canCompareIdenticalSellers() {
        doReturn(seller.getId()).when(sellerMock).getId();
        doReturn(seller.getName()).when(sellerMock).getName();
        doReturn(seller.getBirthdate()).when(sellerMock).getBirthdate();
        doReturn(seller.getEmail()).when(sellerMock).getEmail();
        doReturn(seller.getPhoneNumber()).when(sellerMock).getPhoneNumber();
        doReturn(seller.getBio()).when(sellerMock).getBio();
        doReturn(seller.getCreatedAt()).when(sellerMock).getCreatedAt();

        assertThat(seller).isEqualTo(sellerMock);
    }

    @Test
    public void canCompareDifferentSellers() {
        doReturn(seller.getId()).when(sellerMock).getId();
        doReturn("Different name").when(sellerMock).getName();
        doReturn(seller.getBirthdate()).when(sellerMock).getBirthdate();
        doReturn(seller.getEmail()).when(sellerMock).getEmail();
        doReturn(seller.getPhoneNumber()).when(sellerMock).getPhoneNumber();
        doReturn(seller.getBio()).when(sellerMock).getBio();
        doReturn(seller.getCreatedAt()).when(sellerMock).getCreatedAt();

        assertThat(seller).isNotEqualTo(sellerMock);
    }
}
