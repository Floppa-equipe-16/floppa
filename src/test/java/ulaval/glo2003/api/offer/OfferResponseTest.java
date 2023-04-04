package ulaval.glo2003.api.offer;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.OfferUtils;

public class OfferResponseTest {

    private OfferResponse offerResponse;

    @BeforeEach
    public void setUp() {
        offerResponse = OfferUtils.createOfferResponse();
    }

    @Test
    public void offerResponseEqualsToHimSelf() {
        assertThat(offerResponse).isEqualTo(offerResponse);
    }

    @Test
    public void offerResponseEqualsToOfferResponse() {
        OfferResponse newOfferResponse = OfferUtils.createOfferResponse();

        assertThat(offerResponse).isEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenMessageDiff() {
        OfferResponse newOfferResponse = OfferUtils.createOfferResponse();
        offerResponse.message = "new message";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenAmountDiff() {
        OfferResponse newOfferResponse = OfferUtils.createOfferResponse();
        offerResponse.amount = 1d;

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenUsernameDiff() {
        OfferResponse newOfferResponse = OfferUtils.createOfferResponse();
        offerResponse.username = "new username";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }

    @Test
    public void offerResponseNotEqualsToOfferResponseWhenCreatedAtDiff() {
        OfferResponse newOfferResponse = OfferUtils.createOfferResponse();
        offerResponse.createdAt = "????";

        assertThat(offerResponse).isNotEqualTo(newOfferResponse);
    }
}
