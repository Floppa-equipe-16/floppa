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
    Offer offerMock = mock(Offer.class);

    @BeforeEach
    public void prepareProduct() {
        String validTitle = "Iphone XR";
        String validDescription = "A relatively new Iphone working as good as a new one";
        Double validSuggestedPrice = 200d;
        String validCategory = "electronics";
        product = new Product(validTitle, validDescription, validSuggestedPrice, validCategory);
    }

    @Test
    public void canAddValidOffer() {
        doReturn("usertest").when(offerMock).getUsername();
        doReturn(200d).when(offerMock).getAmount();

        product.addOffer(offerMock);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }
}
