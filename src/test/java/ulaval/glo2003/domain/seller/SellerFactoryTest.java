package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class SellerFactoryTest {

    private SellerFactory factory;
    private static final String NAME = "Alice";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Alice@email.com";
    private static final String PHONE_NUMBER = "11234567890";
    private static final String BIO = "This";

    @BeforeEach
    public void setUp() {
        factory = new SellerFactory();
    }

    @Test
    public void canCreateValidSellerCheckID() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getId()).isNotEmpty();
    }

    @Test
    public void canCreateValidSellerCheckCreateAt() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getCreatedAt()).isNotEmpty();
    }

    @Test
    public void canCreateValidSellerCheckName() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getName()).isEqualTo(NAME);
    }

    @Test
    public void canCreateValidSellerCheckBirthdate() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getBirthdate()).isEqualTo(BIRTHDATE);
    }

    @Test
    public void canCreateValidSellerCheckEmail() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void canCreateValidSellerCheckPhoneNumber() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }

    @Test
    public void canCreateValidSellerCheckBio() {
        Seller seller = factory.createSeller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        assertThat(seller.getBio()).isEqualTo(BIO);
    }

    @Test
    public void createSellerThrowsWhenArgsAreEmpty() {
        assertThrows(InvalidParamException.class, () -> factory.createSeller("", "", "", "", ""));
    }
}
