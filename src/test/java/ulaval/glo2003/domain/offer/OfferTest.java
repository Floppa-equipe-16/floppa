package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        otherOffer = new Offer(
                OfferTestUtils.ID_2,
                OfferTestUtils.PRODUCT_ID,
                OfferTestUtils.USERNAME,
                OfferTestUtils.LOWEST_AMOUNT,
                OfferTestUtils.MESSAGE,
                OfferTestUtils.CREATED_AT);

        assertThat(offer).isNotEqualTo(otherOffer);
    }
}
