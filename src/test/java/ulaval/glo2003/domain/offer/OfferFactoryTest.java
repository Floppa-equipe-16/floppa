package ulaval.glo2003.domain.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OfferFactoryTest {
    private static final String PRODUCT_ID = "Pid";
    private static final String USERNAME = "Bob";
    private static final String MESSAGE = "123456789-123456789-123456798-123456789-123456789-123456789-123456789-123456789-123456789-123456789-";

    private OfferFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new OfferFactory();
    }

    @Test
    public void canCreateOffer() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, 10d, MESSAGE);

        assertThat(offer.getId()).isNotEmpty();
        assertThat(offer.getProductId()).isEqualTo(PRODUCT_ID);
        assertThat(offer.getCreatedAt()).isNotEmpty();
        assertThat(offer.getUsername()).isEqualTo(USERNAME);
        assertThat(offer.getAmount()).isEqualTo(10d);
        assertThat(offer.getMessage()).isEqualTo(MESSAGE);
    }

    @Test
    public void createOfferRoundsAmount() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, 10.12345d, MESSAGE);

        assertThat(offer.getAmount()).isEqualTo(10.12d);
    }

    @Test
    public void createOfferThrowsWhenArgsAreEmpty() {
        assertThrows(InvalidParamException.class, () -> factory.createOffer("", "", 0d, ""));
    }
}