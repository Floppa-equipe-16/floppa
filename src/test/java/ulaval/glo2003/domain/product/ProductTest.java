package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.domain.offer.Offer;

public class ProductTest {
    private static final String ID = "1";
    private static final String SELLER_ID = "2a74sfs3d2g48";
    private static final String TITLE = "Iphone XR";
    private static final String DESCRIPTION = "A relatively new Iphone working as good as a new one";
    private static final double SUGGESTED_PRICE = 200d;
    private static final String CATEGORY = "electronics";

    private Product product;

    @Mock
    private Offer offerMock = mock(Offer.class);

    @BeforeEach
    public void setUp() {
        product = new Product(ID, SELLER_ID, TITLE, Instant.MAX.toString(), DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
    }

    @Test
    public void canCopyProduct() {
        Product productCopy = new Product(product);

        assertThat(productCopy).isEqualTo(product);
    }

    @Test
    public void canAddValidOffer() {
        doReturn("usertest").when(offerMock).getUsername();
        doReturn(200d).when(offerMock).getAmount();

        product.addOffer(offerMock);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }

    @Test
    public void canCompareIdenticalProducts() {
        Product identicalProduct =
                new Product(ID, SELLER_ID, TITLE, Instant.MAX.toString(), DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product).isEqualTo(identicalProduct);
    }

    @Test
    public void canCompareDifferentProducts() {
        Product differentProduct =
                new Product("ABC", SELLER_ID, TITLE, Instant.MAX.toString(), DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product).isNotEqualTo(differentProduct);
    }
}
