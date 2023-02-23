package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class SellerValidatorTest {

    @Mock
    Seller sellerMock = mock(Seller.class);

    private void setAllValidatorToValid(MockedStatic<SellerValidator> sellerValidatorMockedStatic) {
        sellerValidatorMockedStatic
                .when(() -> SellerValidator.isNameInvalid(any()))
                .thenReturn(false);
        sellerValidatorMockedStatic
                .when(() -> SellerValidator.isBirthdateInvalid(any()))
                .thenReturn(false);
        sellerValidatorMockedStatic
                .when(() -> SellerValidator.isPhoneNumberInvalid(any()))
                .thenReturn(false);
        sellerValidatorMockedStatic
                .when(() -> SellerValidator.isEmailInvalid(any()))
                .thenReturn(false);
        sellerValidatorMockedStatic
                .when(() -> SellerValidator.isBioInvalid(any()))
                .thenReturn(false);
    }

    @Test
    void validateWithInvalidName() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isNameInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerMock));

            assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'name'.");
        }
    }

    @Test
    void validateWithInvalidBirthdate() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isBirthdateInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerMock));

            assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'birthdate'.");
        }
    }

    @Test
    void validateWithInvalidEmail() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isEmailInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerMock));

            assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'email'.");
        }
    }

    @Test
    void validateWithInvalidPhoneNumber() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isPhoneNumberInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerMock));

            assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'phone number'.");
        }
    }

    @Test
    void validateWithInvalidBio() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isBioInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerMock));

            assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'bio'.");
        }
    }

    @Test
    void isNameInvalid() {
        String emptyName = " \r \t \n";

        assertTrue(SellerValidator.isNameInvalid(emptyName));
    }

    @Test
    void isBioInvalid() {
        String emptyBio = " \r \t \n";

        assertTrue(SellerValidator.isBioInvalid(emptyBio));
    }

    @Test
    void isPhoneNumberTooLong() {
        String phoneNumber = "1234567890123";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void isPhoneNumberTooShort() {
        String phoneNumber = "123456789";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void isPhoneNumberContainOtherChar() {
        String phoneNumber = "+1234567890";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void isPhoneNumberValid() {
        String phoneNumber = "11234567890";

        assertFalse(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void isEmailValid() {
        String validEmail = "Bob@bob.bob";

        assertFalse(SellerValidator.isEmailInvalid(validEmail));
    }

    @Test
    void isEmailMissingAt() {
        String Email = "Bobbob.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    void isEmailMissingDot() {
        String Email = "Bob@bobbob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    void isEmailMissingIdentifier() {
        String Email = "@bob.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    void isEmailMissingExtension() {
        String Email = "bob@bob.";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    void isEmailMissingService() {
        String Email = "Bob@.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    void isBirthdateFormatInvalid() {
        String invalidDate = "2001-13-20";

        assertTrue(SellerValidator.isBirthdateFormatInvalid(invalidDate));
    }

    @Test
    void isAgeOldEnoughWithOffsetCheck() {
        OffsetDateTime fixedDateTime = OffsetDateTime.of(2018, 1, 2, 2, 0, 0, 0, ZoneOffset.ofHours(3));
        String invalidBirthDate1 = "2000-01-03";
        String invalidBirthDate2 = "2000-01-02";
        String validDate1 = "2000-01-01";

        try (MockedStatic<OffsetDateTime> offsetDateTimeMockedStatic =
                Mockito.mockStatic(OffsetDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            offsetDateTimeMockedStatic.when(OffsetDateTime::now).thenReturn(fixedDateTime);
            assertFalse(SellerValidator.isOldEnough(invalidBirthDate1));
            assertFalse(SellerValidator.isOldEnough(invalidBirthDate2));
            assertTrue(SellerValidator.isOldEnough(validDate1));
        }
    }
}
