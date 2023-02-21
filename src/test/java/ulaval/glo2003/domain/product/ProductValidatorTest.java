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

public class ProductValidatorTest {

    private ProductValidator productValidator;

    @Mock
    private Product productMock = mock(Product.class);

    @Mock
    private Offer offerMock = mock(Offer.class);

    @Spy
    private ProductValidator productValidatorSpy;

    @BeforeEach
    void ProductParamValidatorTest() {
        productValidator = new ProductValidator(productMock);
        productValidatorSpy = spy(productValidator);

        setAllValidatorInSpyToValid();
    }

    private void setAllValidatorInSpyToValid() {
        doReturn(false).when(productValidatorSpy).isSellerIdInvalid(Mockito.any());
        doReturn(false).when(productValidatorSpy).isTitleInvalid(Mockito.any());
        doReturn(false).when(productValidatorSpy).isDescriptionInvalid(Mockito.any());
        doReturn(false).when(productValidatorSpy).isCategoryInvalid(Mockito.any());
        doReturn(false).when(productValidatorSpy).isSuggestedPriceInvalid(Mockito.any());
        doReturn(false).when(productValidatorSpy).isOfferAmountLessThenSuggestedPrice(Mockito.any());
        doReturn(false).when(productValidatorSpy).hasBuyerAlreadyMadeAnOffer(Mockito.any());
    }

    @Test
    void testInvalidSellerIdInValidateParamThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isSellerIdInvalid(Mockito.any());

        InvalidParamException thrownInvalidId =
                assertThrows(InvalidParamException.class, () -> productValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidId.errorDescription.description).isEqualTo("Invalid parameter 'id'.");
    }

    @Test
    void testInvalidTitleInValidateParamThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isTitleInvalid(Mockito.any());

        InvalidParamException thrownInvalidTitle =
                assertThrows(InvalidParamException.class, () -> productValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidTitle.errorDescription.description).isEqualTo("Invalid parameter 'title'.");
    }

    @Test
    void testInvalidDescriptionInValidateParamThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isDescriptionInvalid(Mockito.any());

        InvalidParamException thrownInvalidDescription =
                assertThrows(InvalidParamException.class, () -> productValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidDescription.errorDescription.description).isEqualTo("Invalid parameter 'description'.");
    }

    @Test
    void testInvalidCategoryInValidateParamThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isCategoryInvalid(Mockito.any());

        InvalidParamException thrownInvalidCategory =
                assertThrows(InvalidParamException.class, () -> productValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidCategory.errorDescription.description).isEqualTo("Invalid parameter 'category'.");
    }

    @Test
    void testInvalidSuggestedPriceInValidateParamThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isSuggestedPriceInvalid(Mockito.any());

        InvalidParamException thrownInvalidSuggestedPrice =
                assertThrows(InvalidParamException.class, () -> productValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidSuggestedPrice.errorDescription.description)
                .isEqualTo("Invalid parameter 'suggested price'.");
    }

    @Test
    void testInvalidAmountInvalidateOfferEligibleThrowIfInvalid() {
        doReturn(true).when(productValidatorSpy).isOfferAmountLessThenSuggestedPrice(Mockito.any());

        InvalidParamException thrownInvalidAmount = assertThrows(
                InvalidParamException.class, () -> productValidatorSpy.validateOfferEligibleThrowIfInvalid(offerMock));
        assertThat(thrownInvalidAmount.errorDescription.description).isEqualTo("Invalid parameter 'amount'.");
    }

    @Test
    void testInvalidUsernameInvalidateOfferEligibleThrowIfInvalid() {
        String username = "Username Test";
        doReturn(username).when(offerMock).getUsername();
        doReturn(true).when(productValidatorSpy).hasBuyerAlreadyMadeAnOffer(Mockito.any());

        NotPermittedException thrownInvalidUsername = assertThrows(
                NotPermittedException.class, () -> productValidatorSpy.validateOfferEligibleThrowIfInvalid(offerMock));
        assertThat(thrownInvalidUsername.errorDescription.description)
                .isEqualTo("Not permitted action 'user with username `" + username + "` has already made an offer'.");
    }

    @Test
    void testIsIdInvalid() {
        String invalidId = " \n \r \t";

        assertTrue(productValidator.isSellerIdInvalid(invalidId));
    }

    @Test
    void testIsTitleInvalid() {
        String invalidTitle = " \n \r \t";

        assertTrue(productValidator.isTitleInvalid(invalidTitle));
    }

    @Test
    void testIsDescriptionInvalid() {
        String invalidDescription = " \n \r \t";

        assertTrue(productValidator.isDescriptionInvalid(invalidDescription));
    }

    @Test
    void testIsCategoryInvalid() {
        String invalidCategory = "InvalidCategory";

        assertTrue(productValidator.isCategoryInvalid(invalidCategory));
    }

    @Test
    void testIsSuggestedPriceInvalid() {
        Double suggestedPrice = 0.5;

        assertTrue(productValidator.isSuggestedPriceInvalid(suggestedPrice));
    }

    @Test
    void testIsOfferAmountLessThenSuggestedPrice() {
        doReturn(20d).when(productMock).getSuggestedPrice();
        Double offerAmount = 19.9d;

        assertTrue(productValidator.isOfferAmountLessThenSuggestedPrice(offerAmount));
    }

    @Test
    void testHasBuyerAlreadyMadeAnOffer() {
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(offerMock);
        String username = "Username Test";
        String notAddedUsername = "test";

        doReturn(username).when(offerMock).getUsername();
        doReturn(offers).when(productMock).getOffers();

        assertTrue(productValidator.hasBuyerAlreadyMadeAnOffer(username));
        assertFalse(productValidator.hasBuyerAlreadyMadeAnOffer(notAddedUsername));
    }
}
