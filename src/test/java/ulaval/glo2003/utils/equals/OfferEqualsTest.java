package ulaval.glo2003.utils.equals;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OfferResponse;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.utils.OfferUtils;

public class OfferEqualsTest {

    private Offer offer;

    @BeforeEach
    public void setUp() {
        offer = OfferUtils.createOffer();
    }

    @Test
    public void offerRequestEqualsToOffer() {
        OfferRequest request = OfferUtils.createOfferRequest();

        assertThat(OfferEquals.OfferRequestEqualsOffer(request, offer)).isTrue();
    }

    @Test
    public void offerRequestNotEqualsToOfferWhenMessageDiff() {
        OfferRequest request = OfferUtils.createOfferRequest();
        request.message = "new message";

        assertThat(OfferEquals.OfferRequestEqualsOffer(request, offer)).isFalse();
    }

    @Test
    public void offerRequestNotEqualsToOfferWhenAmountDiff() {
        OfferRequest request = OfferUtils.createOfferRequest();
        request.amount = 1d;

        assertThat(OfferEquals.OfferRequestEqualsOffer(request, offer)).isFalse();
    }

    @Test
    public void offerResponseEqualsToOffer() {
        OfferResponse response = OfferUtils.createOfferResponse();

        assertThat(OfferEquals.OfferResponseEqualsOffer(response, offer)).isTrue();
    }

    @Test
    public void productResponseNotEqualsToProductWhenMessageDiff() {
        OfferResponse response = OfferUtils.createOfferResponse();
        response.message = "new message";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response, offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenAmountDiff() {
        OfferResponse response = OfferUtils.createOfferResponse();
        response.amount = 1d;

        assertThat(OfferEquals.OfferResponseEqualsOffer(response, offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenUsernameDiff() {
        OfferResponse response = OfferUtils.createOfferResponse();
        response.username = "new username";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response, offer)).isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCreateAtDiff() {
        OfferResponse response = OfferUtils.createOfferResponse();
        response.createdAt = "???";

        assertThat(OfferEquals.OfferResponseEqualsOffer(response, offer)).isFalse();
    }
}
