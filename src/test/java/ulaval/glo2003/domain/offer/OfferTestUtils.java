package ulaval.glo2003.domain.offer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.offer.OfferResponse;

public class OfferTestUtils {
    public static final String ID = "1";
    public static final String ID_2 = "2";
    public static final String PRODUCT_ID = "PRODUCT";
    public static final String USERNAME = "2000-01-01";
    public static final Double LOWEST_AMOUNT = 25d;
    public static final Double HIGHEST_AMOUNT = 75d;
    public static final String MESSAGE =
            "This is a description of at least a hundred characters. Let me count them: one, two, three, four, five.";
    public static final String CREATED_AT = Instant.MAX.toString();
    public static final Boolean SELECTED = false;

    public static Offer createOffer() {
        return new Offer(ID, PRODUCT_ID, USERNAME, LOWEST_AMOUNT, MESSAGE, CREATED_AT, SELECTED);
    }

    public static Offer createOfferStub() {
        Offer offer = mock(Offer.class);
        when(offer.getId()).thenReturn(ID);
        when(offer.getCreatedAt()).thenReturn(CREATED_AT);
        when(offer.getProductId()).thenReturn(PRODUCT_ID);
        when(offer.getUsername()).thenReturn(USERNAME);
        when(offer.getAmount()).thenReturn(LOWEST_AMOUNT);
        when(offer.getMessage()).thenReturn(MESSAGE);
        return offer;
    }

    public static Offer createOfferStub2() {
        Offer offer = mock(Offer.class);
        when(offer.getId()).thenReturn(ID_2);
        when(offer.getCreatedAt()).thenReturn(CREATED_AT);
        when(offer.getProductId()).thenReturn(PRODUCT_ID);
        when(offer.getUsername()).thenReturn(USERNAME);
        when(offer.getAmount()).thenReturn(HIGHEST_AMOUNT);
        when(offer.getMessage()).thenReturn(MESSAGE);
        return offer;
    }

    public static OfferRequest createOfferRequest() {
        OfferRequest request = new OfferRequest();
        request.amount = LOWEST_AMOUNT;
        request.message = MESSAGE;

        return request;
    }

    public static OfferResponse createOfferResponse() {
        return new OfferResponse(createOffer());
    }
}
