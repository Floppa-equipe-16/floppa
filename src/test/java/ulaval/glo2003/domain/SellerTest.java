package ulaval.glo2003.domain;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class SellerTest {
    private final static String VALID_NAME = "Bob";
    private final static String VALID_BIRTHDATE = "2000-01-01";
    private final static String VALID_PHONE_NUMBER = "14181234567";
    private final static String VALID_EMAIL = "bob@gmail.com";
    private final static String VALID_BIO = "My name is Bob!";
    private final Product validProduct = new Product("Title", "Description", 200.0, "electronics");
    private Seller seller;

    @Spy
    Seller sellerSpy;

    @BeforeEach
    public void initSeller() {
        seller = new Seller(getSellerParams());
        sellerSpy = spy(seller);
    }

    private SellerParams getSellerParams() {
        SellerParams params = new SellerParams();
        params.name = VALID_NAME;
        params.birthdate = VALID_BIRTHDATE;
        params.email = VALID_EMAIL;
        params.phoneNumber = VALID_PHONE_NUMBER;
        params.bio = VALID_BIO;

        return params;
    }

    @Test
    public void constructorThrowsWhenNameInvalid() {
        InvalidParamException invalidParamException = new InvalidParamException("name");
        String invalidName = " \n \r \t";
        SellerParams invalidParams = getSellerParams();
        invalidParams.name = invalidName;

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");

        assertThat(thrownInvalidName.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBirthdateInvalidFormat() {
        InvalidParamException invalidParamException = new InvalidParamException("birthdate");
        String invalidFormat = "2001/12/20";
        SellerParams invalidParams = getSellerParams();
        invalidParams.birthdate = invalidFormat;

        InvalidParamException thrownInvalidFormat = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidFormat.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBirthdateInvalidDate() {
        InvalidParamException invalidParamException = new InvalidParamException("birthdate");
        String invalidDate = "2001-13-20";
        SellerParams invalidParams = getSellerParams();
        invalidParams.birthdate = invalidDate;

        InvalidParamException thrownInvalidDate = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
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
        SellerParams invalidParams = getSellerParams();
        invalidParams.email = invalidEmail;

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingAt() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob.com";
        SellerParams invalidParams = getSellerParams();
        invalidParams.email = invalidEmail;

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingService() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@.com";
        SellerParams invalidParams = getSellerParams();
        invalidParams.email = invalidEmail;

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingDot() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@gmailcom";
        SellerParams invalidParams = getSellerParams();
        invalidParams.email = invalidEmail;

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenEmailMissingExtension() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail = "Bob@gmail.";
        SellerParams invalidParams = getSellerParams();
        invalidParams.email = invalidEmail;

        InvalidParamException thrownInvalidEmail = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidEmail.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberIsTooLong() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "111234567890";
        SellerParams invalidParams = getSellerParams();
        invalidParams.phoneNumber = invalidPhoneNumber;

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberIsTooShort() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "1234567890";
        SellerParams invalidParams = getSellerParams();
        invalidParams.phoneNumber = invalidPhoneNumber;

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberContainOtherChar() {
        InvalidParamException invalidParamException = new InvalidParamException("phone number");
        String invalidPhoneNumber = "+1234567890";
        SellerParams invalidParams = getSellerParams();
        invalidParams.phoneNumber = invalidPhoneNumber;

        InvalidParamException thrownInvalidPhoneNumber = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");
        assertThat(thrownInvalidPhoneNumber.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBioInvalid() {
        InvalidParamException invalidParamException = new InvalidParamException("bio");
        String invalidBio = " \n \r \t";
        SellerParams invalidParams = getSellerParams();
        invalidParams.bio = invalidBio;

        InvalidParamException thrownInvalidBio = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidParams),
                "Expected new Seller() to throw");

        assertThat(thrownInvalidBio.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void canAddProduct() {
        seller.addProduct(validProduct);
        Optional<Product> product = Optional.ofNullable(seller.getProducts().get(0));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void canGetProductResponses() {
        ProductResponse productResponseValid = new ProductResponse(validProduct);
        seller.addProduct(validProduct);
        ProductResponse productResponseFromSeller = seller.getProductResponses().get(0);

        assertThat(seller.getProductResponses().size()).isEqualTo(1);
        assertThat(productResponseFromSeller.id).isEqualTo(productResponseValid.id);
    }
}
