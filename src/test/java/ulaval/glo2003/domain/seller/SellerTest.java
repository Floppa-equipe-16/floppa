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
    void initSeller() {
        String validName = "Bob";
        String validBirthdate = "2000-01-01";
        String validPhoneNumber = "14181234567";
        String validEmail = "bob@gmail.com";
        String validBio = "My name is Bob!";

        seller = new Seller(validName, validBirthdate, validEmail, validPhoneNumber, validBio);

        doReturn("id-test").when(productMock).getId();
    }

    @Test
    void testCopyConstructor() {
        Seller sellerCopy = new Seller(seller);

        assertThat(seller).isEqualTo(sellerCopy);
    }

    @Test
    void canAddProduct() {
        seller.addProduct(productMock);
        Optional<Product> product = Optional.ofNullable(seller.getProductById(productMock.getId()));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    void doesNotAddSameProductTwice() {
        seller.addProduct(productMock);
        seller.addProduct(productMock);

        assertThat(seller.getProducts().size()).isEqualTo(1);
    }

    @Test
    void testEqualsFunction() {
        doReturn(seller.getId()).when(sellerMock).getId();
        doReturn(seller.getName()).when(sellerMock).getName();
        doReturn(seller.getBirthdate()).when(sellerMock).getBirthdate();
        doReturn(seller.getEmail()).when(sellerMock).getEmail();
        doReturn(seller.getPhoneNumber()).when(sellerMock).getPhoneNumber();
        doReturn(seller.getBio()).when(sellerMock).getBio();
        doReturn(seller.getCreatedAt()).when(sellerMock).getCreatedAt();

        assertThat(seller).isEqualTo(sellerMock);
    }
}
