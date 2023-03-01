package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

public class ProductValidatorTest {

    @Mock
    private Product productMock = mock(Product.class);

    @Mock
    private Offer offerMock = mock(Offer.class);

    private MockedStatic<ProductValidator> setAllValidatorToValid(
            MockedStatic<ProductValidator> productValidatorMockedStatic) {
        productValidatorMockedStatic
                .when(() -> ProductValidator.isSellerIdInvalid(any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.isTitleInvalid(any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.isDescriptionInvalid(any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.isCategoryInvalid(any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.isSuggestedPriceInvalid(any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.isOfferAmountLessThenSuggestedPrice(any(), any()))
                .thenReturn(false);
        productValidatorMockedStatic
                .when(() -> ProductValidator.hasBuyerAlreadyMadeAnOffer(any(), any()))
                .thenReturn(false);
        return productValidatorMockedStatic;
    }

    @Test
    public void validateThrowsWithInvalidSellerId() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isSellerIdInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidId =
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productMock));

            assertThat(thrownInvalidId.errorDescription.description).isEqualTo("Invalid parameter 'id'.");
        }
    }

    @Test
    public void validateThrowsWithInvalidTitle() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isTitleInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidTitle =
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productMock));

            assertThat(thrownInvalidTitle.errorDescription.description).isEqualTo("Invalid parameter 'title'.");
        }
    }

    @Test
    public void validateThrowsWithInvalidDescription() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isDescriptionInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidDescription =
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productMock));

            assertThat(thrownInvalidDescription.errorDescription.description)
                    .isEqualTo("Invalid parameter 'description'.");
        }
    }

    @Test
    public void validateThrowsWithInvalidCategory() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isCategoryInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidCategory =
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productMock));

            assertThat(thrownInvalidCategory.errorDescription.description).isEqualTo("Invalid parameter 'category'.");
        }
    }

    @Test
    public void validateThrowsWithInvalidSuggestedPrice() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isSuggestedPriceInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidSuggestedPrice =
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productMock));

            assertThat(thrownInvalidSuggestedPrice.errorDescription.description)
                    .isEqualTo("Invalid parameter 'suggested price'.");
        }
    }

    @Test
    public void validateOfferEligibleThrowsWithInvalidAmount() {
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.isOfferAmountLessThenSuggestedPrice(any(), any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidAmount = assertThrows(
                    InvalidParamException.class, () -> ProductValidator.validateOfferEligible(productMock, offerMock));

            assertThat(thrownInvalidAmount.errorDescription.description).isEqualTo("Invalid parameter 'amount'.");
        }
    }

    @Test
    public void validateOfferEligibleThrowsWithInvalidUsername() {
        String username = "Username Test";
        doReturn(username).when(offerMock).getUsername();
        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.hasBuyerAlreadyMadeAnOffer(any(), any()))
                    .thenReturn(true);

            NotPermittedException thrownInvalidUsername = assertThrows(
                    NotPermittedException.class, () -> ProductValidator.validateOfferEligible(productMock, offerMock));

            assertThat(thrownInvalidUsername.errorDescription.description)
                    .isEqualTo(
                            "Not permitted action 'user with username `" + username + "` has already made an offer'.");
        }
    }

    @Test
    public void canCheckIsSellerIdInvalid() {
        String invalidId = " \n \r \t";

        assertTrue(ProductValidator.isSellerIdInvalid(invalidId));
    }

    @Test
    public void canCheckIsTitleInvalid() {
        String invalidTitle = " \n \r \t";

        assertTrue(ProductValidator.isTitleInvalid(invalidTitle));
    }

    @Test
    public void canCheckIsDescriptionInvalid() {
        String invalidDescription = " \n \r \t";

        assertTrue(ProductValidator.isDescriptionInvalid(invalidDescription));
    }

    @Test
    public void canCheckIsCategoryInvalid() {
        String invalidCategory = "InvalidCategory";

        assertTrue(ProductValidator.isCategoryInvalid(invalidCategory));
    }

    @Test
    public void canCheckIsSuggestedPriceInvalid() {
        Double suggestedPrice = 0.5;

        assertTrue(ProductValidator.isSuggestedPriceInvalid(suggestedPrice));
    }

    @Test
    public void canCheckIsOfferAmountLessThenSuggestedPrice() {
        doReturn(20d).when(productMock).getSuggestedPrice();
        Double offerAmount = 19.9d;

        assertTrue(ProductValidator.isOfferAmountLessThenSuggestedPrice(productMock, offerAmount));
    }

    @Test
    public void canCheckHasBuyerAlreadyMadeAnOffer() {
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(offerMock);
        String username = "Username Test";
        String notAddedUsername = "test";

        doReturn(username).when(offerMock).getUsername();
        doReturn(offers).when(productMock).getOffers();

        assertTrue(ProductValidator.hasBuyerAlreadyMadeAnOffer(productMock, username));
        assertFalse(ProductValidator.hasBuyerAlreadyMadeAnOffer(productMock, notAddedUsername));
    }
}
