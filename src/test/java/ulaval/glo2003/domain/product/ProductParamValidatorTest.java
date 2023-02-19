package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

public class ProductParamValidatorTest {

    private ProductParamValidator productParamValidator;

    @Mock
    Product productMock = mock(Product.class);

    @Mock
    Offer offerMock = mock(Offer.class);

    @Spy
    ProductParamValidator productParamValidatorSpy;

    @BeforeEach
    public void ProductParamValidatorTest() {
        productParamValidator = new ProductParamValidator(productMock);
        productParamValidatorSpy = spy(productParamValidator);

        doReturn(false).when(productParamValidatorSpy).isTitleInvalid(Mockito.any());
        doReturn(false).when(productParamValidatorSpy).isDescriptionInvalid(Mockito.any());
        doReturn(false).when(productParamValidatorSpy).isCategoryInvalid(Mockito.any());
        doReturn(false).when(productParamValidatorSpy).isSuggestedPriceInvalid(Mockito.any());
        doReturn(false).when(productParamValidatorSpy).isOfferAmountLessThenSuggestedPrice(Mockito.any());
        doReturn(false).when(productParamValidatorSpy).hasBuyerAlreadyMadeAnOffer(Mockito.any());
    }

    @Test
    public void testInvalidTitleInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(productParamValidatorSpy).isTitleInvalid(Mockito.any());

        InvalidParamException thrownInvalidTitle = assertThrows(
                InvalidParamException.class, () -> productParamValidatorSpy.validateProductParamThrowIfInvalid());
        assertThat(thrownInvalidTitle.errorDescription.description).isEqualTo("Invalid parameter 'title'.");
    }

    @Test
    public void testInvalidDescriptionInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(productParamValidatorSpy).isDescriptionInvalid(Mockito.any());

        InvalidParamException thrownInvalidDescription = assertThrows(
                InvalidParamException.class, () -> productParamValidatorSpy.validateProductParamThrowIfInvalid());
        assertThat(thrownInvalidDescription.errorDescription.description).isEqualTo("Invalid parameter 'description'.");
    }

    @Test
    public void testInvalidCategoryInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(productParamValidatorSpy).isCategoryInvalid(Mockito.any());

        InvalidParamException thrownInvalidCategory = assertThrows(
                InvalidParamException.class, () -> productParamValidatorSpy.validateProductParamThrowIfInvalid());
        assertThat(thrownInvalidCategory.errorDescription.description).isEqualTo("Invalid parameter 'category'.");
    }

    @Test
    public void testInvalidSuggestedPriceInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(productParamValidatorSpy).isSuggestedPriceInvalid(Mockito.any());

        InvalidParamException thrownInvalidSuggestedPrice = assertThrows(
                InvalidParamException.class, () -> productParamValidatorSpy.validateProductParamThrowIfInvalid());
        assertThat(thrownInvalidSuggestedPrice.errorDescription.description)
                .isEqualTo("Invalid parameter 'suggested price'.");
    }

    @Test
    public void testInvalidAmountInvalidateOfferEligibleThrowIfInvalid() {
        doReturn(true).when(productParamValidatorSpy).isOfferAmountLessThenSuggestedPrice(Mockito.any());

        InvalidParamException thrownInvalidAmount = assertThrows(
                InvalidParamException.class,
                () -> productParamValidatorSpy.validateOfferEligibleThrowIfInvalid(offerMock));
        assertThat(thrownInvalidAmount.errorDescription.description).isEqualTo("Invalid parameter 'amount'.");
    }

    @Test
    public void testInvalidUsernameInvalidateOfferEligibleThrowIfInvalid() {
        String username = "Username Test";
        doReturn(username).when(offerMock).getUsername();
        doReturn(true).when(productParamValidatorSpy).hasBuyerAlreadyMadeAnOffer(Mockito.any());

        NotPermittedException thrownInvalidUsername = assertThrows(
                NotPermittedException.class,
                () -> productParamValidatorSpy.validateOfferEligibleThrowIfInvalid(offerMock));
        assertThat(thrownInvalidUsername.errorDescription.description)
                .isEqualTo("Not permitted action 'user with username `" + username + "` has already made an offer'.");
    }

    @Test
    public void testIsTitleInvalid() {
        String invalidTitle = " \n \r \t";

        assertTrue(productParamValidator.isTitleInvalid(invalidTitle));
    }

    @Test
    public void testIsDescriptionInvalid() {
        String invalidDescription = " \n \r \t";

        assertTrue(productParamValidator.isDescriptionInvalid(invalidDescription));
    }

    @Test
    public void testIsCategoryInvalid() {
        String invalidCategory = "InvalidCategory";

        assertTrue(productParamValidator.isCategoryInvalid(invalidCategory));
    }

    @Test
    public void testIsSuggestedPriceInvalid() {
        Double suggestedPrice = 0.5;

        assertTrue(productParamValidator.isSuggestedPriceInvalid(suggestedPrice));
    }

    @Test
    public void testIsOfferAmountLessThenSuggestedPrice() {
        doReturn(20d).when(productMock).getSuggestedPrice();
        Double offerAmount = 19.9d;

        assertTrue(productParamValidator.isOfferAmountLessThenSuggestedPrice(offerAmount));
    }

    @Test
    public void testHasBuyerAlreadyMadeAnOffer() {
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(offerMock);
        String username = "Username Test";
        String notAddedUsername = "test";

        doReturn(username).when(offerMock).getUsername();
        doReturn(offers).when(productMock).getOffers();

        assertTrue(productParamValidator.hasBuyerAlreadyMadeAnOffer(username));
        assertFalse(productParamValidator.hasBuyerAlreadyMadeAnOffer(notAddedUsername));
    }
}
