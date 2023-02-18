package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.product.Product;

public class SellerTest {

    private Seller seller;
    private final String validName = "Bob";
    private final String validBirthdate = "2000-01-01";
    private final String validPhoneNumber = "14181234567";
    private final String validEmail = "bob@gmail.com";
    private final String validBio = "My name is Bob!";
    private final Product validProduct = new Product("Title", "Description", 200.0, "electronics");

    @Spy
    Seller sellerSpy;

    @BeforeEach
    public void initSeller() {
        seller = new Seller(validName, validBirthdate, validEmail, validPhoneNumber, validBio);
        sellerSpy = spy(seller);
    }

    @Test
    public void constructorThrowsWhenNameInvalid() {
        InvalidParamException invalidParamException = new InvalidParamException("name");
        String invalidName = " \n \r \t";

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidName, validBirthdate, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");

        assertThat(thrownInvalidName.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBirthdateInvalidFormat() {
        InvalidParamException invalidParamException = new InvalidParamException("birthdate");
        String invalidFormat = "2001/12/20";

        InvalidParamException thrownInvalidFormat = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, invalidFormat, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidFormat.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBirthdateInvalidDate() {
        InvalidParamException invalidParamException = new InvalidParamException("birthdate");
        String invalidDate = "2001-13-20";

        InvalidParamException thrownInvalidDate = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, invalidDate, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidDate.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    void functionIs18orMoreCheckWithOffset() {
        OffsetDateTime fixedDateTime = OffsetDateTime.of(2018, 1, 2, 2, 0, 0, 0, ZoneOffset.ofHours(3));
        String invalidBirthDate1 = "2000-01-03";
        String invalidBirthDate2 = "2000-01-02";
        String validDate1 = "2000-01-01";

        when(sellerSpy.currentTime()).thenReturn(fixedDateTime);

        assertFalse(sellerSpy.is18orMore(invalidBirthDate1));
        assertFalse(sellerSpy.is18orMore(invalidBirthDate2));
        assertTrue(sellerSpy.is18orMore(validDate1));
    }

    @Test
    public void constructorThrowsWhenEmailMissingIdentifier() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "@Bob.com";

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingAt() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob.com";

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingService() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@.com";

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingDot() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@gmailcom";

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingExtension() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@gmail.";

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberIsTooLong() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "111234567890";

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberIsTooShort() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "1234567890";

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberContainOtherChar() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "+1234567890";

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber, validBio),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBioInvalid() {
        InvalidParamException invalidParamException = new InvalidParamException("bio");
        String invalidBio = " \n \r \t";

        InvalidParamException thrownInvalidBio = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, validPhoneNumber, invalidBio),
                "Expected new Seller() to throw");

        assertThat(thrownInvalidBio.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void canAddProduct() {
        seller.addProduct(validProduct);
        Optional<Product> product = Optional.ofNullable(seller.getProductById(validProduct.getId()));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }
}
