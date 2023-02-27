package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.domain.offer.Offer;

public class ProductTest {

    private Product product;

    @Mock
    private Offer offerMock = mock(Offer.class);

    @Mock
    private Product productMock = mock(Product.class);

    @BeforeEach
    void prepareProduct() {
        String validSellerId = "2a74sfs3d2g48";
        String validTitle = "Iphone XR";
        String validDescription = "A relatively new Iphone working as good as a new one";
        Double validSuggestedPrice = 200d;
        String validCategory = "electronics";

        product = new Product(validSellerId, validTitle, validDescription, validSuggestedPrice, validCategory);
    }

    @Test
    void copyConstructor() {
        Product productCopy = new Product(product);

        assertThat(productCopy).isEqualTo(product);
    }

    @Test
    void canAddValidOffer() {
        doReturn("usertest").when(offerMock).getUsername();
        doReturn(200d).when(offerMock).getAmount();

        product.addOffer(offerMock);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }

    @Test
    void canCompareIdenticalProducts() {
        doReturn(product.getId()).when(productMock).getId();
        doReturn(product.getSellerId()).when(productMock).getSellerId();
        doReturn(product.getTitle()).when(productMock).getTitle();
        doReturn(product.getDescription()).when(productMock).getDescription();
        doReturn(product.getSuggestedPrice()).when(productMock).getSuggestedPrice();
        doReturn(product.getCategory()).when(productMock).getCategory();
        doReturn(product.getCreatedAt()).when(productMock).getCreatedAt();

        assertThat(product).isEqualTo(productMock);
    }

    @Test
    void canCompareDifferentProducts() {
        doReturn(product.getId()).when(productMock).getId();
        doReturn(product.getSellerId()).when(productMock).getSellerId();
        doReturn("Different title").when(productMock).getTitle();
        doReturn(product.getDescription()).when(productMock).getDescription();
        doReturn(product.getSuggestedPrice()).when(productMock).getSuggestedPrice();
        doReturn(product.getCategory()).when(productMock).getCategory();
        doReturn(product.getCreatedAt()).when(productMock).getCreatedAt();

        assertThat(product).isNotEqualTo(productMock);
    }
}
