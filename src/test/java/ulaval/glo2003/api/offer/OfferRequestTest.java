package ulaval.glo2003.api.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferTestUtils;

public class OfferRequestTest {

    private OfferRequest offerRequest;

    @BeforeEach
    public void setUp() {
        offerRequest = OfferTestUtils.createOfferRequest();

    }

    @Test
    public void canValidateValidRequest() {
        assertDoesNotThrow(offerRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullAmount() {
        offerRequest.amount = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullMessage() {
        offerRequest.message = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }

    @Test
    public void offerRequestEqualsToHimSelf(){
        assertThat(offerRequest).isEqualTo(offerRequest);
    }

    @Test
    public void offerRequestEqualsToofferRequest(){
        OfferRequest newofferRequest = OfferTestUtils.createOfferRequest();

        assertThat(offerRequest).isEqualTo(newofferRequest);
    }

    @Test
    public void offerRequestNotEqualsToofferRequest(){
        OfferRequest newofferRequest = OfferTestUtils.createOfferRequest();
        newofferRequest.message = "new message";

        assertThat(offerRequest).isNotEqualTo(newofferRequest);
    }

    @Test
    public void offerRequestEqualsTooffer(){
        Offer offer = OfferTestUtils.createOffer();

        assertThat(offerRequest).isEqualTo(offer);
    }

    @Test
    public void offerRequestNotEqualsTooffer(){
        Offer offer = OfferTestUtils.createOffer();
        offerRequest.message = "new message";

        assertThat(offerRequest).isNotEqualTo(offer);
    }
}
