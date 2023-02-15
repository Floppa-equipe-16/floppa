package ulaval.glo2003.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class SellerTest {

    private Seller seller;
    private final String validName = "Bob";
    private final String validBirthdate = "2000-01-01";
    private final String validPhoneNumber = "14181234567";
    private final String validEmail = "bob@gmail.com";
    private final String validBio = "My name is Bob!";
    private final Product validProduct = new Product("Title","Description",200.0,"electronics");

    @Spy
    Seller sellerSpy;

    @BeforeEach
    public void initSeller(){
        seller = new Seller(validName, validBirthdate, validEmail,validPhoneNumber, validBio);
        sellerSpy = spy(seller);
    }

    @Test
    public void constructorThrowsWhenNameInvalid(){
        InvalidParamException invalidParamException =  new InvalidParamException("name");
        String invalidName = " \n \r \t";

        InvalidParamException thrownInvalidName = assertThrows(
                InvalidParamException.class,
                () -> new Seller(invalidName, validBirthdate, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );

        assertThat(thrownInvalidName.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBirthdateFormatInvalid(){
        InvalidParamException invalidParamException =  new InvalidParamException("birthdate");
        String invalidDate = "2001-13-20";
        String invalidFormat = "2001/12/20";


        InvalidParamException thrownInvalidDate = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, invalidDate, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidDate.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

        InvalidParamException thrownInvalidFormat = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, invalidFormat, validEmail, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidFormat.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

    }

    @Test
    void functionIs18OrMoreCheckWithOffset() {
        OffsetDateTime fixedDateTime = OffsetDateTime
                .of(2018, 1, 2, 17, 0, 0, 0, ZoneOffset.ofHours(18));
        String invalidBirthDate1 = "2000-01-03";
        String invalidBirthDate2 = "2000-01-02";
        String validDate1 = "2000-01-01";


        when(sellerSpy.currentTime()).thenReturn(fixedDateTime);

        assertFalse(sellerSpy.is18orMore(invalidBirthDate1));
        assertFalse(sellerSpy.is18orMore(invalidBirthDate2));
        assertTrue(sellerSpy.is18orMore(validDate1));
    }

    @Test
    public void constructorThrowsWhenEmailInvalid() {
        InvalidParamException invalidParamException = new InvalidParamException("email");
        String invalidEmail1 = "Bob.com";
        String invalidEmail2 = "Bob@gmail";
        String invalidEmail3 = "Bob@gmail.";

        InvalidParamException thrownInvalidEmail1 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail1, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidEmail1.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

        InvalidParamException thrownInvalidEmail2 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail2, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidEmail2.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

        InvalidParamException thrownInvalidEmail3 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, invalidEmail3, validPhoneNumber, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidEmail3.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenPhoneNumberInvalid(){
        InvalidParamException invalidParamException =  new InvalidParamException("phone number");
        String invalidPhoneNumber1 = "+1234567890";
        String invalidPhoneNumber2 = "1-418-123-4567";
        String invalidPhoneNumber3 = "1234567890";

        InvalidParamException thrownInvalidPhoneNumber1 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber1, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidPhoneNumber1.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

        InvalidParamException thrownInvalidPhoneNumber2 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber2, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidPhoneNumber2.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);

        InvalidParamException thrownInvalidPhoneNumber3 = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, invalidPhoneNumber3, validBio),
                "Expected new Seller() to throw"
        );
        assertThat(thrownInvalidPhoneNumber3.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void constructorThrowsWhenBioInvalid(){
        InvalidParamException invalidParamException =  new InvalidParamException("bio");
        String invalidBio = " \n \r \t";

        InvalidParamException thrownInvalidBio = assertThrows(
                InvalidParamException.class,
                () -> new Seller(validName, validBirthdate, validEmail, validPhoneNumber, invalidBio),
                "Expected new Seller() to throw"
        );

        assertThat(thrownInvalidBio.errorDescription.description)
                .isEqualTo(invalidParamException.errorDescription.description);
    }

    @Test
    public void canAddProduct(){
        seller.addProduct(validProduct);
        Optional<Product> product = Optional.ofNullable(seller.getProducts().get(0));

        assertThat(seller.getProducts().size()).isEqualTo(1);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void canGetProductResponses(){
        ProductResponse productResponseValid = new ProductResponse(validProduct);
        seller.addProduct(validProduct);
        ProductResponse productResponseFromSeller = seller.getProductResponses().get(0);

        assertThat(seller.getProductResponses().size()).isEqualTo(1);
        assertThat(productResponseFromSeller.id).isEqualTo(productResponseValid.id);
    }
}
