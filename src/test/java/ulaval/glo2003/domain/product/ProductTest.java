package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

public class ProductTest {

    private Product product;
    private final String validTitle = "Iphone XR";
    private final String validDescription = "A relatively new Iphone working as good as a new one";
    private final Double validSuggestedPrice = 200d;
    private final String validCategory = "electronics";

    private final Offer validOffer = new Offer(
            "User0",
            200d,
            "Lorem ipsum dolor sit amet, consectetur"
                    + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

    @BeforeEach
    public void prepareProduct() {
        product = new Product(validTitle, validDescription, validSuggestedPrice, validCategory);
    }

    @Test
    public void constructorThrowsWhenEmptyTitle() {
        String emptyTitle = " \n\t";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(emptyTitle, validDescription, validSuggestedPrice, validCategory));
    }

    @Test
    public void constructorThrowsWhenEmptyDescription() {
        String emptyDescription = " \n\t";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(validTitle, emptyDescription, validSuggestedPrice, validCategory));
    }

    @Test
    public void constructorThrowsWhenSuggestedPriceUnder1() {
        Double under1SuggestedPrice = 0.5d;

        assertThrows(
                InvalidParamException.class,
                () -> new Product(validTitle, validDescription, under1SuggestedPrice, validCategory));
    }

    @Test
    public void constructorThrowsWhenCategoryDoesntExist() {
        String inexistentCategory = "Apple products";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(validTitle, validDescription, validSuggestedPrice, inexistentCategory));
    }

    @Test
    public void canAddValidOffer() {
        product.addOffer(validOffer);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }

    @Test
    public void addOfferMethodThrowsWhenOfferAmountLowerSuggestedPrice() {
        Offer invalidOffer = new Offer(validOffer.getUsername(), 150d, validOffer.getMessage());

        assertThrows(InvalidParamException.class, () -> product.addOffer(invalidOffer));
    }

    @Test
    public void addOfferMethodThrowsWhenBuyerAlreadyMadeOffer() {
        product.addOffer(validOffer);
        Offer otherOfferSameUser = new Offer(validOffer.getUsername(), validOffer.getAmount(), validOffer.getMessage());

        assertThrows(NotPermittedException.class, () -> product.addOffer(otherOfferSameUser));
    }
}
