package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferFactory;

class OfferMapperTest {
    private static final String PRODUCT_ID = "PRODUCT";
    private static final String BUYER_NAME = "Bob";
    private static final Double HIGHEST_AMOUNT = 64.50d;
    private static final Double LOWEST_AMOUNT = 35.50d;
    private static final String MESSAGE =
            "This is a description of at least a hundred characters. Let me count them: one, two, three, four, five.";

    @Mock
    private OfferFactory factory = mock(OfferFactory.class);

    private OfferMapper mapper;
    private List<Offer> offerStubs;

    @BeforeEach
    public void setUp() {
        mapper = new OfferMapper(factory);
        offerStubs = new ArrayList<>();
        offerStubs.add(createOfferMock("1", LOWEST_AMOUNT));
        offerStubs.add(createOfferMock("2", HIGHEST_AMOUNT));
    }

    private Offer createOfferMock(String id, double amount) {
        Offer offer = mock(Offer.class);
        when(offer.getId()).thenReturn(id);
        when(offer.getProductId()).thenReturn(PRODUCT_ID);
        when(offer.getUsername()).thenReturn(BUYER_NAME);
        when(offer.getAmount()).thenReturn(amount);
        when(offer.getMessage()).thenReturn(MESSAGE);

        return offer;
    }

    @Test
    public void canMapRequestToOffer() {
        doReturn(offerStubs.get(0)).when(factory).createOffer(PRODUCT_ID, BUYER_NAME, LOWEST_AMOUNT, MESSAGE);
        OfferRequest request = createRequest();

        Offer offer = mapper.requestToOffer(PRODUCT_ID, BUYER_NAME, request);

        assertThat(offer.getProductId()).isEqualTo(PRODUCT_ID);
        assertThat(offer.getUsername()).isEqualTo(BUYER_NAME);
        assertThat(offer.getAmount()).isEqualTo(request.amount);
        assertThat(offer.getMessage()).isEqualTo(request.message);
    }

    private OfferRequest createRequest() {
        OfferRequest request = new OfferRequest();
        request.amount = LOWEST_AMOUNT;
        request.message = MESSAGE;

        return request;
    }

    @Test
    public void canCreateSummaryResponseWithMultipleOffers() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertDescriptiveFieldsAreNull(response);
    }

    @Test
    public void createSummaryResponseIsEmptyWithNoOffer() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

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
    public void canCreateCompleteResponseWithMultipleOffers() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertThat(response.maxAmount).isEqualTo(HIGHEST_AMOUNT);
        assertThat(response.minAmount).isEqualTo(LOWEST_AMOUNT);
        assertThat(response.offers).isNotEmpty();
    }

    @Test
    public void createCompleteResponseIsEmptyWithNoOffer() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertThat(response.maxAmount).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.offers).isEmpty();
    }
}
