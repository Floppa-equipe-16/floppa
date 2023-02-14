package ulaval.glo2003.domain;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.exceptionHandling.InvalidParamException;
import ulaval.glo2003.api.exceptionHandling.NotPermittedException;

public class ProductTest {

    private Product product;
    private String validTitle;
    private String validDescription;
    private Double validSuggestedPrice;
    private String validCategory;

    @BeforeEach
    public void prepareValidParameters() {
        validTitle = "Iphone XR";
        validDescription = "A relatively new Iphone working as good as a new one";
        validSuggestedPrice = 200d;
        validCategory = "electronics";
    }

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
        Offer validOffer = new Offer(
                "User0",
                200d,
                "Lorem ipsum dolor sit amet, consectetur"
                        + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        product.addOffer(validOffer);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }

    @Test
    public void addOfferMethodthrowsWhenOfferAmountLowerSuggestedPrice() {
        Offer invalidOffer = new Offer(
                "User0",
                100d,
                "Lorem ipsum dolor sit amet, consectetur"
                        + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        assertThrows(InvalidParamException.class, () -> product.addOffer(invalidOffer));
    }

    @Test
    public void addOfferMethodthrowsWhenBuyerAlreadyMadeOffer() {
        Offer offer = new Offer(
                "User0",
                200d,
                "Lorem ipsum dolor sit amet, consectetur"
                        + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Offer otherOfferBySameUser = new Offer(
                "User0",
                250d,
                "Lorem ipsum dolor sit amet, consectetur"
                        + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        product.addOffer(offer);

        assertThrows(NotPermittedException.class, () -> product.addOffer(otherOfferBySameUser));
    }
}
