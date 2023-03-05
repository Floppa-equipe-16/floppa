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
    Seller sellerStub = mock(Seller.class);

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
    public void validateThrowsWithInvalidName() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isNameInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerStub));

            assertThat(thrownInvalidName.errorDescription.description)
                    .ignoringCase()
                    .contains("name");
        }
    }

    @Test
    public void validateThrowsWithInvalidBirthdate() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isBirthdateInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerStub));

            assertThat(thrownInvalidName.errorDescription.description)
                    .ignoringCase()
                    .contains("birthdate");
        }
    }

    @Test
    public void validateThrowsWithInvalidEmail() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isEmailInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerStub));

            assertThat(thrownInvalidName.errorDescription.description)
                    .ignoringCase()
                    .contains("email");
        }
    }

    @Test
    public void validateThrowsWithInvalidPhoneNumber() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isPhoneNumberInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerStub));

            assertThat(thrownInvalidName.errorDescription.description)
                    .ignoringCase()
                    .contains("phone number");
        }
    }

    @Test
    public void validateThrowsWithInvalidBio() {
        try (MockedStatic<SellerValidator> sellerValidatorMockedStatic =
                Mockito.mockStatic(SellerValidator.class, Mockito.CALLS_REAL_METHODS)) {
            setAllValidatorToValid(sellerValidatorMockedStatic);
            sellerValidatorMockedStatic
                    .when(() -> SellerValidator.isBioInvalid(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidName =
                    assertThrows(InvalidParamException.class, () -> SellerValidator.validateParam(sellerStub));

            assertThat(thrownInvalidName.errorDescription.description)
                    .ignoringCase()
                    .contains("bio");
        }
    }

    @Test
    public void canCheckIsNameInvalid() {
        String emptyName = " \r \t \n";

        assertTrue(SellerValidator.isNameInvalid(emptyName));
    }

    @Test
    public void canCheckIsBioInvalid() {
        String emptyBio = " \r \t \n";

        assertTrue(SellerValidator.isBioInvalid(emptyBio));
    }

    @Test
    public void canCheckIsPhoneNumberTooLong() {
        String phoneNumber = "1234567890123";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void canCheckIsPhoneNumberTooShort() {
        String phoneNumber = "123456789";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void canCheckIsPhoneNumberContainsOtherChar() {
        String phoneNumber = "+1234567890";

        assertTrue(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void canCheckIsPhoneNumberValid() {
        String phoneNumber = "11234567890";

        assertFalse(SellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void canCheckIsEmailValid() {
        String validEmail = "Bob@bob.bob";

        assertFalse(SellerValidator.isEmailInvalid(validEmail));
    }

    @Test
    public void canCheckIsEmailMissingAt() {
        String Email = "Bobbob.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    public void canCheckIsEmailMissingDot() {
        String Email = "Bob@bobbob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    public void canCheckIsEmailMissingIdentifier() {
        String Email = "@bob.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    public void canCheckIsEmailMissingExtension() {
        String Email = "bob@bob.";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    public void canCheckIsEmailMissingService() {
        String Email = "Bob@.bob";

        assertTrue(SellerValidator.isEmailInvalid(Email));
    }

    @Test
    public void canCheckIsBirthdateFormatInvalid() {
        String invalidDate = "2001-13-20";

        assertTrue(SellerValidator.isBirthdateFormatInvalid(invalidDate));
    }

    @Test
    public void canCheckIsAgeOldEnoughWithOffsetCheck() {
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

    @Test
    public void isOldEnoughReturnsFalseWhenFormatIsInvalid() {
        OffsetDateTime fixedDateTime = OffsetDateTime.of(2018, 1, 2, 2, 0, 0, 0, ZoneOffset.ofHours(3));
        String invalidDate = "August 25, 2032";

        try (MockedStatic<OffsetDateTime> offsetDateTimeMockedStatic =
                Mockito.mockStatic(OffsetDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            offsetDateTimeMockedStatic.when(OffsetDateTime::now).thenReturn(fixedDateTime);
            assertFalse(SellerValidator.isOldEnough(invalidDate));
        }
    }
}
