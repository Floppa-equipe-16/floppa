package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class SellerValidatorTest {

    private SellerValidator sellerValidator;

    @Mock
    Seller sellerMock = mock(Seller.class);

    @Spy
    SellerValidator sellerValidatorSpy;

    @BeforeEach
    void SellerParamValidatorTest() {
        sellerValidator = new SellerValidator(sellerMock);
        sellerValidatorSpy = spy(sellerValidator);

        setAllValidatorInSpyToValid();
    }

    private void setAllValidatorInSpyToValid() {
        doReturn(false).when(sellerValidatorSpy).isNameInvalid(Mockito.any());
        doReturn(false).when(sellerValidatorSpy).isBirthdateInvalid(Mockito.any());
        doReturn(false).when(sellerValidatorSpy).isPhoneNumberInvalid(Mockito.any());
        doReturn(false).when(sellerValidatorSpy).isEmailInvalid(Mockito.any());
        doReturn(false).when(sellerValidatorSpy).isBioInvalid(Mockito.any());
    }

    @Test
    void testInvalidNameInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerValidatorSpy).isNameInvalid(Mockito.any());

        InvalidParamException thrownInvalidName =
                assertThrows(InvalidParamException.class, () -> sellerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'name'.");
    }

    @Test
    void testInvalidBirthdateInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerValidatorSpy).isBirthdateInvalid(Mockito.any());

        InvalidParamException thrownInvalidName =
                assertThrows(InvalidParamException.class, () -> sellerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'birthdate'.");
    }

    @Test
    void testInvalidEmailInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerValidatorSpy).isEmailInvalid(Mockito.any());

        InvalidParamException thrownInvalidName =
                assertThrows(InvalidParamException.class, () -> sellerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'email'.");
    }

    @Test
    void testInvalidPhoneNumberInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerValidatorSpy).isPhoneNumberInvalid(Mockito.any());

        InvalidParamException thrownInvalidName =
                assertThrows(InvalidParamException.class, () -> sellerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'phone number'.");
    }

    @Test
    void testInvalidBioInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerValidatorSpy).isBioInvalid(Mockito.any());

        InvalidParamException thrownInvalidName =
                assertThrows(InvalidParamException.class, () -> sellerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'bio'.");
    }

    @Test
    void testisNameInvalid() {
        String emptyName = " \r \t \n";

        assertTrue(sellerValidator.isNameInvalid(emptyName));
    }

    @Test
    void testisBioInvalid() {
        String emptyBio = " \r \t \n";

        assertTrue(sellerValidator.isBioInvalid(emptyBio));
    }

    @Test
    void testIsPhoneNumberTooLong() {
        String phoneNumber = "1234567890123";

        assertTrue(sellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void testIsPhoneNumberTooShort() {
        String phoneNumber = "123456789";

        assertTrue(sellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void testIsPhoneNumberContainOtherChar() {
        String phoneNumber = "+1234567890";

        assertTrue(sellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void testIsPhoneNumberValid() {
        String phoneNumber = "11234567890";

        assertFalse(sellerValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    void testIsEmailValid() {
        String validEmail = "Bob@bob.bob";

        assertFalse(sellerValidator.isEmailInvalid(validEmail));
    }

    @Test
    void testIsEmailMissingAt() {
        String Email = "Bobbob.bob";

        assertTrue(sellerValidator.isEmailInvalid(Email));
    }

    @Test
    void testIsEmailMissingDot() {
        String Email = "Bob@bobbob";

        assertTrue(sellerValidator.isEmailInvalid(Email));
    }

    @Test
    void testIsEmailMissingIdentifier() {
        String Email = "@bob.bob";

        assertTrue(sellerValidator.isEmailInvalid(Email));
    }

    @Test
    void testIsEmailMissingExtension() {
        String Email = "bob@bob.";

        assertTrue(sellerValidator.isEmailInvalid(Email));
    }

    @Test
    void testIsEmailMissingService() {
        String Email = "Bob@.bob";

        assertTrue(sellerValidator.isEmailInvalid(Email));
    }

    @Test
    void testisBirthdateFormatInvalid() {
        String invalidDate = "2001-13-20";

        assertTrue(sellerValidator.isBirthdateFormatInvalid(invalidDate));
    }

    @Test
    void testIs18orMoreCheckWithOffset() {
        OffsetDateTime fixedDateTime = OffsetDateTime.of(2018, 1, 2, 2, 0, 0, 0, ZoneOffset.ofHours(3));
        String invalidBirthDate1 = "2000-01-03";
        String invalidBirthDate2 = "2000-01-02";
        String validDate1 = "2000-01-01";

        doReturn(fixedDateTime).when(sellerValidatorSpy).currentTime();

        assertFalse(sellerValidatorSpy.isBirthdate18orMore(invalidBirthDate1));
        assertFalse(sellerValidatorSpy.isBirthdate18orMore(invalidBirthDate2));
        assertTrue(sellerValidatorSpy.isBirthdate18orMore(validDate1));
    }
}
