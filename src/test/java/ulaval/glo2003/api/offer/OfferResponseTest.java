package ulaval.glo2003.api.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferTestUtils;

import static com.google.common.truth.Truth.assertThat;

public class OfferResponseTest {

    private OfferResponse offerResponse;

    @BeforeEach
    public void setUp() {
        offerResponse = OfferTestUtils.createOfferResponse();
    }

    @Test
    public void offerResponseEqualsToHimSelf(){
        assertThat(offerResponse).isEqualTo(offerResponse);
    }

    @Test
    public void offerResponseEqualsToOfferResponse(){
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();

        assertThat(offerResponse).isEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponse(){
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();
        offerResponse.message = "new message";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseEqualsToOffer(){
        Offer offer = OfferTestUtils.createOffer();

        assertThat(offerResponse).isEqualTo(offer);
    }

    @Test
    public void productResponseNotEqualsToProduct(){
        Offer offer = OfferTestUtils.createOffer();
        offerResponse.message = "new message";

        assertThat(offerResponse).isNotEqualTo(offer);
    }
}
