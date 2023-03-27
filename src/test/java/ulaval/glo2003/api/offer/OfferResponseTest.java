package ulaval.glo2003.api.offer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.offer.OfferTestUtils;

public class OfferResponseTest {

    private OfferResponse offerResponse;

    @BeforeEach
    public void setUp() {
        offerResponse = OfferTestUtils.createOfferResponse();
    }

    @Test
    public void offerResponseEqualsToHimSelf() {
        assertThat(offerResponse).isEqualTo(offerResponse);
    }

    @Test
    public void offerResponseEqualsToOfferResponse() {
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();

        assertThat(offerResponse).isEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenMessageDiff() {
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();
        offerResponse.message = "new message";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenAmountDiff() {
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();
        offerResponse.amount = 1d;

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenUsernameDiff() {
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();
        offerResponse.username = "new username";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenCreateAtDiff() {
        OfferResponse newOfferResponse = OfferTestUtils.createOfferResponse();
        offerResponse.createdAt = "????";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }


}
