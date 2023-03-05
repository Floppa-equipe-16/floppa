package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OfferTest {
    private static final String ID = "1";
    private static final String OTHER_ID = "2";
    private static final String PRODUCT_ID = "10";
    private static final String USERNAME = "Alice";
    private static final double AMOUNT = 200d;
    private static final String MESSAGE = "One item please";
    private static final String CREATION_DATE = Instant.MAX.toString();

    private Offer offer;
    private Offer otherOffer;

    @BeforeEach
    public void setUp() {
        offer = createOffer(ID);
    }

    private Offer createOffer(String id) {
        return new Offer(id, PRODUCT_ID, USERNAME, AMOUNT, MESSAGE, CREATION_DATE);
    }

    @Test
    public void canCopyOffer() {
        Offer offerCopy = new Offer(offer);

        assertThat(offerCopy).isEqualTo(offer);
    }

    @Test
    public void canCompareIdenticalOffers() {
        otherOffer = createOffer(ID);

        assertThat(offer).isEqualTo(otherOffer);
    }

    @Test
    public void canCompareDifferentOffers() {
        otherOffer = createOffer(OTHER_ID);

        assertThat(offer).isNotEqualTo(otherOffer);
    }
}
