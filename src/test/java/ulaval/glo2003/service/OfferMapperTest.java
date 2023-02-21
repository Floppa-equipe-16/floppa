package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.domain.offer.Offer;

class OfferMapperTest {
    private static final String PRODUCT_ID = "PRODUCT";
    private static final String BUYER_NAME = "Bob";
    private static final Double HIGHEST_AMOUNT = 64.50d;
    private static final Double LOWEST_AMOUNT = 35.50d;
    private static final String DESCRIPTION =
            "This is a description of at least a hundred characters. Let me count them: one, two, three, four, five.";

    private List<Offer> offers;

    @BeforeEach
    protected void setUp() {
        offers = new ArrayList<>();
        offers.add(new Offer(PRODUCT_ID, "Alice", HIGHEST_AMOUNT, DESCRIPTION));
        offers.add(new Offer(PRODUCT_ID, "Bob", LOWEST_AMOUNT, DESCRIPTION));
    }

    @Test
    void canMapRequestToOffer() {
        OfferRequest request = createRequest();

        Offer offer = OfferMapper.requestToOffer(PRODUCT_ID, BUYER_NAME, request);

        assertThat(offer.getProductId()).isEqualTo(PRODUCT_ID);
        assertThat(offer.getUsername()).isEqualTo(BUYER_NAME);
        assertThat(offer.getAmount()).isEqualTo(request.amount);
        assertThat(offer.getMessage()).isEqualTo(request.message);
    }

    private OfferRequest createRequest() {
        OfferRequest request = new OfferRequest();
        request.amount = HIGHEST_AMOUNT;
        request.message = DESCRIPTION;

        return request;
    }

    @Test
    protected void canCreateSummaryResponseWithMultipleOffers() {
        OfferCollectionResponse response = OfferMapper.offersToSummaryCollectionResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertDescriptiveFieldsAreNull(response);
    }

    @Test
    protected void createSummaryResponseIsEmptyWithNoOffer() {
        OfferCollectionResponse response = OfferMapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertDescriptiveFieldsAreNull(response);
    }

    private void assertDescriptiveFieldsAreNull(OfferCollectionResponse response) {
        assertThat(response.offers).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.maxAmount).isNull();
    }

    @Test
    protected void canCreateCompleteResponseWithMultipleOffers() {
        OfferCollectionResponse response = OfferMapper.offersToCompleteCollectionResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertThat(response.maxAmount).isEqualTo(HIGHEST_AMOUNT);
        assertThat(response.minAmount).isEqualTo(LOWEST_AMOUNT);
        assertThat(response.offers).isNotEmpty();
    }

    @Test
    protected void createCompleteResponseIsEmptyWithNoOffer() {
        OfferCollectionResponse response = OfferMapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertThat(response.maxAmount).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.offers).isEmpty();
    }
}
