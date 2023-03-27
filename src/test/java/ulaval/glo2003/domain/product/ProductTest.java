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

    @BeforeEach
    public void setUp() {
        product = ProductTestUtils.createProduct();
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
        Product identicalProduct = ProductTestUtils.createProduct();

        assertThat(product).isEqualTo(identicalProduct);
    }

    @Test
    public void canCompareDifferentProducts() {
        Product differentProduct =
                new Product(
                        "ABC",
                        ProductTestUtils.SELLER_ID,
                        ProductTestUtils.TITLE,
                        ProductTestUtils.CREATE_AT,
                        ProductTestUtils.DESCRIPTION,
                        ProductTestUtils.SUGGESTED_PRICE,
                        ProductTestUtils.CATEGORY);

        assertThat(product).isNotEqualTo(differentProduct);
    }
}
