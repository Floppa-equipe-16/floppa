package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class OfferFactoryTest {
    private static final String PRODUCT_ID = "Pid";
    private static final String USERNAME = "Bob";
    private static final String MESSAGE =
            "123456789-123456789-123456798-123456789-123456789-123456789-123456789-123456789-123456789-123456789-";
    private static final Double AMOUNT = 10d;
    private OfferFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new OfferFactory();
    }

    @Test
    public void canCreateOfferCheckOfferId() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

        assertThat(offer.getId()).isNotEmpty();
    }

    @Test
    public void canCreateOfferCheckProductId() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

        assertThat(offer.getProductId()).isEqualTo(PRODUCT_ID);
    }

    @Test
    public void canCreateOfferCheckCreateAt() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

        assertThat(offer.getCreatedAt()).isNotEmpty();
    }

    @Test
    public void canCreateOfferCheckUsername() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

        assertThat(offer.getUsername()).isEqualTo(USERNAME);
    }

    @Test
    public void canCreateOfferCheckAmount() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

        assertThat(offer.getAmount()).isEqualTo(AMOUNT);
    }

    @Test
    public void canCreateOfferCheckMessage() {
        Offer offer = factory.createOffer(PRODUCT_ID, USERNAME, AMOUNT, MESSAGE);

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
