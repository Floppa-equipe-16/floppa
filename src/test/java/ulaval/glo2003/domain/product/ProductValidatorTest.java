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
    private Product productStub = mock(Product.class);

    @Mock
    private Offer offerStub = mock(Offer.class);

    private void setAllValidatorToValid(MockedStatic<ProductValidator> productValidatorMockedStatic) {
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
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productStub));

            assertThat(thrownInvalidId.errorDescription.description)
                    .ignoringCase()
                    .contains("id");
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
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productStub));

            assertThat(thrownInvalidTitle.errorDescription.description)
                    .ignoringCase()
                    .contains("title");
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
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productStub));

            assertThat(thrownInvalidDescription.errorDescription.description)
                    .ignoringCase()
                    .contains("description");
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
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productStub));

            assertThat(thrownInvalidCategory.errorDescription.description)
                    .ignoringCase()
                    .contains("category");
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
                    assertThrows(InvalidParamException.class, () -> ProductValidator.validateParam(productStub));

            assertThat(thrownInvalidSuggestedPrice.errorDescription.description)
                    .ignoringCase()
                    .contains("suggested price");
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
                    InvalidParamException.class, () -> ProductValidator.validateOfferEligible(productStub, offerStub));

            assertThat(thrownInvalidAmount.errorDescription.description)
                    .ignoringCase()
                    .contains("amount");
        }
    }

    @Test
    public void validateOfferEligibleThrowsWithInvalidUsername() {
        String username = "Username Test";
        doReturn(username).when(offerStub).getUsername();

        try (MockedStatic<ProductValidator> productValidatorMockedStatic =
                Mockito.mockStatic(ProductValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(productValidatorMockedStatic);
            productValidatorMockedStatic
                    .when(() -> ProductValidator.hasBuyerAlreadyMadeAnOffer(any(), any()))
                    .thenReturn(true);

            NotPermittedException thrownInvalidUsername = assertThrows(
                    NotPermittedException.class, () -> ProductValidator.validateOfferEligible(productStub, offerStub));

            assertThat(thrownInvalidUsername.errorDescription.description)
                    .ignoringCase()
                    .contains(username);
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
        doReturn(20d).when(productStub).getSuggestedPrice();
        Double offerAmount = 19.9d;

        assertTrue(ProductValidator.isOfferAmountLessThenSuggestedPrice(productStub, offerAmount));
    }

    @Test
    public void canCheckHasBuyerAlreadyMadeAnOffer() {
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(offerStub);
        String username = "Username Test";
        String notAddedUsername = "test";

        doReturn(username).when(offerStub).getUsername();
        doReturn(offers).when(productStub).getOffers();

        assertTrue(ProductValidator.hasBuyerAlreadyMadeAnOffer(productStub, username));
        assertFalse(ProductValidator.hasBuyerAlreadyMadeAnOffer(productStub, notAddedUsername));
    }
}
