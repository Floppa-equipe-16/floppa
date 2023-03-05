package ulaval.glo2003.domain.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class SellerFactoryTest {

    private SellerFactory factory;

    // TODO: Besoin de dependency injection pour la validation?
    @BeforeEach
    public void setUp() {
        factory = new SellerFactory();
    }

    @Test
    public void canCreateValidSeller() {
        Seller seller = factory.createSeller("Alice", "2000-01-01", "Alice@email.com", "11234567890", "This");

        assertThat(seller.getId()).isNotEmpty();
        assertThat(seller.getCreatedAt()).isNotEmpty();
        assertThat(seller.getName()).isEqualTo("Alice");
        assertThat(seller.getBirthdate()).isEqualTo("2000-01-01");
        assertThat(seller.getEmail()).isEqualTo("Alice@email.com");
        assertThat(seller.getPhoneNumber()).isEqualTo("11234567890");
        assertThat(seller.getBio()).isEqualTo("This");
    }

    @Test
    public void createSellerThrowsWhenArgsAreEmpty() {
        assertThrows(InvalidParamException.class, () -> factory.createSeller("", "", "", "", ""));
    }
}
