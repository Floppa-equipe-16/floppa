package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.OfferTestUtils;

public class OfferTest {

    private Offer offer;
    private Offer otherOffer;

    @BeforeEach
    public void setUp() {
        offer = OfferTestUtils.createOffer();
    }

    @Test
    public void canCopyOffer() {
        Offer offerCopy = new Offer(offer);

        assertThat(offerCopy).isEqualTo(offer);
    }

    @Test
    public void canCompareIdenticalOffers() {
        otherOffer = OfferTestUtils.createOffer();

        assertThat(offer).isEqualTo(otherOffer);
    }

    @Test
    public void canCompareDifferentOffers() {
        otherOffer = OfferTestUtils.createOffer2();

        assertThat(offer).isNotEqualTo(otherOffer);
    }
}
