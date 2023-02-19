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

    @BeforeEach
    public void initSeller() {
        String validName = "Bob";
        String validBirthdate = "2000-01-01";
        String validPhoneNumber = "14181234567";
        String validEmail = "bob@gmail.com";
        String validBio = "My name is Bob!";

        seller = new Seller(validName, validBirthdate, validEmail, validPhoneNumber, validBio);

        doReturn("id-test").when(productMock).getId();
    }

    @Test
    public void canAddProduct() {
        seller.addProduct(productMock);
        Optional<Product> product = Optional.ofNullable(seller.getProductById(productMock.getId()));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void doesNotAddSameProductTwice() {
        seller.addProduct(productMock);
        seller.addProduct(productMock);

        assertThat(seller.getProducts().size()).isEqualTo(1);
    }
}
