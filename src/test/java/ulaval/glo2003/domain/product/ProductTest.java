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
    private static final String SELLER_ID = "SELLER";
    private static final String VALID_TITLE = "Iphone XR";
    private static final String VALID_PRODUCT_DESCRIPTION = "A relatively new Iphone working as good as a new one";
    private static final Double VALID_SUGGESTED_PRICE = 200d;
    private static final Double VALID_OFFER_AMOUNT = 250d;
    private static final String VALID_CATEGORY = "electronics";
    private static final String VALID_USERNAME = "Alice";
    private static final String VALID_OFFER_MESSAGE = "Lorem ipsum dolor sit amet, consectetur"
            + " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    private Product product;
    private Offer validOffer;

    @BeforeEach
    public void prepareProduct() {
        product = new Product(SELLER_ID, VALID_TITLE, VALID_PRODUCT_DESCRIPTION, VALID_SUGGESTED_PRICE, VALID_CATEGORY);

        validOffer = new Offer(product.getId(), VALID_USERNAME, VALID_OFFER_AMOUNT, VALID_OFFER_MESSAGE);
    }

    @Test
    public void constructorThrowsWhenEmptyTitle() {
        String emptyTitle = " \n\t";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(
                        SELLER_ID, emptyTitle, VALID_PRODUCT_DESCRIPTION, VALID_SUGGESTED_PRICE, VALID_CATEGORY));
    }

    @Test
    public void constructorThrowsWhenEmptyDescription() {
        String emptyDescription = " \n\t";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(SELLER_ID, VALID_TITLE, emptyDescription, VALID_SUGGESTED_PRICE, VALID_CATEGORY));
    }

    @Test
    public void constructorThrowsWhenSuggestedPriceUnder1() {
        Double under1SuggestedPrice = 0.5d;

        assertThrows(
                InvalidParamException.class,
                () -> new Product(
                        SELLER_ID, VALID_TITLE, VALID_PRODUCT_DESCRIPTION, under1SuggestedPrice, VALID_CATEGORY));
    }

    @Test
    public void constructorThrowsWhenCategoryDoesntExist() {
        String nonExistentCategory = "Apple products";

        assertThrows(
                InvalidParamException.class,
                () -> new Product(
                        SELLER_ID, VALID_TITLE, VALID_PRODUCT_DESCRIPTION, VALID_SUGGESTED_PRICE, nonExistentCategory));
    }

    @Test
    public void canAddValidOffer() {
        product.addOffer(validOffer);

        Optional<Offer> offer = Optional.ofNullable(product.getOffers().get(0));
        assertThat(offer.isPresent()).isTrue();
    }

    @Test
    public void addOfferMethodThrowsWhenOfferAmountLowerSuggestedPrice() {
        Offer invalidOffer = new Offer(product.getId(), validOffer.getUsername(), 150d, validOffer.getMessage());

        assertThrows(InvalidParamException.class, () -> product.addOffer(invalidOffer));
    }

    @Test
    public void addOfferMethodThrowsWhenBuyerAlreadyMadeOffer() {
        product.addOffer(validOffer);
        Offer otherOfferSameUser =
                new Offer(product.getId(), validOffer.getUsername(), validOffer.getAmount(), validOffer.getMessage());

        assertThrows(NotPermittedException.class, () -> product.addOffer(otherOfferSameUser));
    }
}
