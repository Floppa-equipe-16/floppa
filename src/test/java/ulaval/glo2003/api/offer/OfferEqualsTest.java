package ulaval.glo2003.api.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferTestUtils;

import static com.google.common.truth.Truth.assertThat;

public class OfferEqualsTest {

    private Offer offer;

    @BeforeEach
    public void setUp(){
        offer = OfferTestUtils.createOffer();
    }

    @Test
    public void offerRequestEqualsToOffer() {
        OfferRequest request = OfferTestUtils.createOfferRequest();

        assertThat(OfferEquals.OfferRequestEqualsOffer(request,offer)).isTrue();
    }

    @Test
    public void offerRequestNotEqualsToOfferWhenMessageDiff() {
        OfferRequest request = OfferTestUtils.createOfferRequest();
        request.message = "new message";

        assertThat(OfferEquals.OfferRequestEqualsOffer(request,offer)).isFalse();
    }

    @Test
    public void offerRequestNotEqualsToOfferWhenAmountDiff() {
        OfferRequest request = OfferTestUtils.createOfferRequest();
        request.amount = 1d;

        assertThat(OfferEquals.OfferRequestEqualsOffer(request,offer)).isFalse();
    }

    @Test
    public void offerResponseEqualsToOffer() {
        OfferResponse response = OfferTestUtils.createOfferResponse();

        assertThat(OfferEquals.OfferResponseEqualsOffer(response,offer)).isTrue();
    }

    @Test
    public void productResponseNotEqualsToProductWhenMessageDiff() {
        OfferResponse response = OfferTestUtils.createOfferResponse();
        response.message = "new message";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response,offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenAmountDiff() {
        OfferResponse response = OfferTestUtils.createOfferResponse();
        response.amount = 1d;

        assertThat(OfferEquals.OfferResponseEqualsOffer(response,offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenUsernameDiff() {
        OfferResponse response = OfferTestUtils.createOfferResponse();
        response.username = "new username";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response,offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCreateAtDiff() {
        OfferResponse response = OfferTestUtils.createOfferResponse();
        response.createdAt = "???";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response,offer)).isFalse();
    }
}
