package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.OfferUtils;

public class OfferTest {

    private Offer offer;
    private Offer otherOffer;

    @BeforeEach
    public void setUp() {
        offer = OfferUtils.createOffer();
    }

    @Test
    public void canCopyOffer() {
        Offer offerCopy = new Offer(offer);

        assertThat(offerCopy).isEqualTo(offer);
    }

    @Test
    public void canCompareIdenticalOffers() {
        otherOffer = OfferUtils.createOffer();

        assertThat(offer).isEqualTo(otherOffer);
    }

    @Test
    public void canCompareDifferentOffers() {
        otherOffer = OfferUtils.createOffer2();

        assertThat(offer).isNotEqualTo(otherOffer);
    }
}
