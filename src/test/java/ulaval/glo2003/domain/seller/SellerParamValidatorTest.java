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

public class SellerParamValidatorTest {

    private SellerParamValidator sellerParamValidator;

    @Mock
    Seller sellerMock = mock(Seller.class);

    @Spy
    SellerParamValidator sellerParamValidatorSpy;

    @BeforeEach
    public void SellerParamValidatorTest() {
        sellerParamValidator = new SellerParamValidator(sellerMock);
        sellerParamValidatorSpy = spy(sellerParamValidator);

        doReturn(false).when(sellerParamValidatorSpy).isNameInvalid(Mockito.any());
        doReturn(false).when(sellerParamValidatorSpy).isBirthdateInvalid(Mockito.any());
        doReturn(false).when(sellerParamValidatorSpy).isPhoneNumberInvalid(Mockito.any());
        doReturn(false).when(sellerParamValidatorSpy).isEmailInvalid(Mockito.any());
        doReturn(false).when(sellerParamValidatorSpy).isBioInvalid(Mockito.any());
    }

    @Test
    void testInvalidNameInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerParamValidatorSpy).isNameInvalid(Mockito.any());

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class, () -> sellerParamValidatorSpy.validateSellerParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'name'.");
    }

    @Test
    public void testInvalidBirthdateInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerParamValidatorSpy).isBirthdateInvalid(Mockito.any());

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class, () -> sellerParamValidatorSpy.validateSellerParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'birthdate'.");
    }

    @Test
    public void testInvalidEmailInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerParamValidatorSpy).isEmailInvalid(Mockito.any());

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class, () -> sellerParamValidatorSpy.validateSellerParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'email'.");
    }

    @Test
    public void testInvalidPhoneNumberInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerParamValidatorSpy).isPhoneNumberInvalid(Mockito.any());

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class, () -> sellerParamValidatorSpy.validateSellerParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'phone number'.");
    }

    @Test
    public void testInvalidBioInValidateSellerParamThrowIfInvalid() {
        doReturn(true).when(sellerParamValidatorSpy).isBioInvalid(Mockito.any());

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class, () -> sellerParamValidatorSpy.validateSellerParamThrowIfInvalid());
        assertThat(thrownInvalidName.errorDescription.description).isEqualTo("Invalid parameter 'bio'.");
    }

    @Test
    public void testisNameInvalid() {
        String emptyName = " \r \t \n";

        assertTrue(sellerParamValidator.isNameInvalid(emptyName));
    }

    @Test
    public void testisBioInvalid() {
        String emptyBio = " \r \t \n";

        assertTrue(sellerParamValidator.isBioInvalid(emptyBio));
    }

    @Test
    public void testIsPhoneNumberTooLong() {
        String phoneNumber = "1234567890123";

        assertTrue(sellerParamValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void testIsPhoneNumberTooShort() {
        String phoneNumber = "123456789";

        assertTrue(sellerParamValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void testIsPhoneNumberContainOtherChar() {
        String phoneNumber = "+1234567890";

        assertTrue(sellerParamValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void testIsPhoneNumberValid() {
        String phoneNumber = "11234567890";

        assertFalse(sellerParamValidator.isPhoneNumberInvalid(phoneNumber));
    }

    @Test
    public void testIsEmailValid() {
        String validEmail = "Bob@bob.bob";

        assertFalse(sellerParamValidator.isEmailInvalid(validEmail));
    }

    @Test
    public void testIsEmailMissingAt() {
        String Email = "Bobbob.bob";

        assertTrue(sellerParamValidator.isEmailInvalid(Email));
    }

    @Test
    public void testIsEmailMissingDot() {
        String Email = "Bob@bobbob";

        assertTrue(sellerParamValidator.isEmailInvalid(Email));
    }

    @Test
    public void testIsEmailMissingIdentifier() {
        String Email = "@bob.bob";

        assertTrue(sellerParamValidator.isEmailInvalid(Email));
    }

    @Test
    public void testIsEmailMissingExtension() {
        String Email = "bob@bob.";

        assertTrue(sellerParamValidator.isEmailInvalid(Email));
    }

    @Test
    public void testIsEmailMissingService() {
        String Email = "Bob@.bob";

        assertTrue(sellerParamValidator.isEmailInvalid(Email));
    }

    @Test
    public void testisBirthdateFormatInvalid() {
        String invalidDate = "2001-13-20";

        assertTrue(sellerParamValidator.isBirthdateFormatInvalid(invalidDate));
    }

    @Test
    public void testIs18orMoreCheckWithOffset() {
        OffsetDateTime fixedDateTime = OffsetDateTime.of(2018, 1, 2, 2, 0, 0, 0, ZoneOffset.ofHours(3));
        String invalidBirthDate1 = "2000-01-03";
        String invalidBirthDate2 = "2000-01-02";
        String validDate1 = "2000-01-01";

        doReturn(fixedDateTime).when(sellerParamValidatorSpy).currentTime();

        assertFalse(sellerParamValidatorSpy.isBirthdate18orMore(invalidBirthDate1));
        assertFalse(sellerParamValidatorSpy.isBirthdate18orMore(invalidBirthDate2));
        assertTrue(sellerParamValidatorSpy.isBirthdate18orMore(validDate1));
    }
}
