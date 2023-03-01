package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.C;
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
    private static final String DESCRIPTION =
            "This is a description of at least a hundred characters. Let me count them: one, two, three, four, five.";
    private static final String CREATION_DATE = "";

    @Mock
    private OfferFactory factory = mock(OfferFactory.class);
    private OfferMapper mapper;
    private List<Offer> offers;

    @BeforeEach
    public  void setUp() {
        mapper = new OfferMapper(factory);
        offers = new ArrayList<>();
        offers.add(new Offer("1", PRODUCT_ID, BUYER_NAME, LOWEST_AMOUNT, DESCRIPTION, CREATION_DATE));
        offers.add(new Offer("2", PRODUCT_ID, "Alice", HIGHEST_AMOUNT, DESCRIPTION, CREATION_DATE));
    }

    @Test
    public void canMapRequestToOffer() {
        doReturn(offers.get(0)).when(factory).createOffer(PRODUCT_ID, BUYER_NAME, LOWEST_AMOUNT, DESCRIPTION);
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
        request.message = DESCRIPTION;

        return request;
    }

    @Test
    public  void canCreateSummaryResponseWithMultipleOffers() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertDescriptiveFieldsAreNull(response);
    }

    @Test
    public  void createSummaryResponseIsEmptyWithNoOffer() {
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
    public  void canCreateCompleteResponseWithMultipleOffers() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertThat(response.maxAmount).isEqualTo(HIGHEST_AMOUNT);
        assertThat(response.minAmount).isEqualTo(LOWEST_AMOUNT);
        assertThat(response.offers).isNotEmpty();
    }

    @Test
    public  void createCompleteResponseIsEmptyWithNoOffer() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertThat(response.maxAmount).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.offers).isEmpty();
    }
}
