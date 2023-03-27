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
import ulaval.glo2003.domain.offer.OfferTestUtils;

class OfferMapperTest {

    @Mock
    private OfferFactory factory = mock(OfferFactory.class);

    private OfferMapper mapper;
    private List<Offer> offerStubs;

    @BeforeEach
    public void setUp() {
        mapper = new OfferMapper(factory);
        offerStubs = new ArrayList<>();
        offerStubs.add(OfferTestUtils.createOfferStub());
        offerStubs.add(OfferTestUtils.createOfferStub2());
    }


    @Test
    public void canMapRequestToOffer() {
        doReturn(offerStubs.get(0)).when(factory).createOffer(anyString(), anyString(), anyDouble(), anyString());
        OfferRequest request = OfferTestUtils.createOfferRequest();

        Offer offer = mapper.requestToOffer(OfferTestUtils.PRODUCT_ID, OfferTestUtils.USERNAME, request);

        assertThat(request).isEqualTo(offer);
    }


    @Test
    public void canCreateSummaryResponseWithMultipleOffersCheckCount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.count).isEqualTo(2);
    }

    @Test
    public void canCreateSummaryResponseWithMultipleOffersCheckAvgAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.avgAmount).isEqualTo(50d);
    }

    @Test
    public void canCreateSummaryResponseWithMultipleOffersCheckMaxAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.maxAmount).isNull();
    }

    @Test
    public void canCreateSummaryResponseWithMultipleOffersCheckMinAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.minAmount).isNull();
    }

    @Test
    public void canCreateSummaryResponseWithMultipleOffersCheckOffers() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(offerStubs);

        assertThat(response.offers).isNull();
    }


    @Test
    public void createSummaryResponseIsEmptyWithNoOfferCheckCount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
    }

    @Test
    public void createSummaryResponseIsEmptyWithNoOfferCheckAvgAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.avgAmount).isNull();
    }

    @Test
    public void createSummaryResponseIsEmptyWithNoOfferCheckMaxAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.maxAmount).isNull();
    }

    @Test
    public void createSummaryResponseIsEmptyWithNoOfferCheckMinAmount() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.minAmount).isNull();
    }

    @Test
    public void createSummaryResponseIsEmptyWithNoOfferCheckOffers() {
        OfferCollectionResponse response = mapper.offersToSummaryCollectionResponse(new ArrayList<>());

        assertThat(response.offers).isNull();
    }


    @Test
    public void canCreateCompleteResponseWithMultipleOffersCheckCount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);

        assertThat(response.count).isEqualTo(2);
    }

    @Test
    public void canCreateCompleteResponseWithMultipleOffersCheckAvgAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);
        Double result = (OfferTestUtils.HIGHEST_AMOUNT + OfferTestUtils.LOWEST_AMOUNT) / 2;

        assertThat(response.avgAmount).isEqualTo(result);
    }

    @Test
    public void canCreateCompleteResponseWithMultipleOffersCheckMaxAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);

        assertThat(response.maxAmount).isEqualTo(OfferTestUtils.HIGHEST_AMOUNT);
    }

    @Test
    public void canCreateCompleteResponseWithMultipleOffersCheckMinAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);

        assertThat(response.minAmount).isEqualTo(OfferTestUtils.LOWEST_AMOUNT);
    }

    @Test
    public void canCreateCompleteResponseWithMultipleOffersCheckOffers() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(offerStubs);

        assertThat(response.offers).isNotEmpty();
    }

    @Test
    public void createCompleteWithNoOfferCheckCount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
    }

    @Test
    public void createCompleteWithNoOfferCheckAvgAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.avgAmount).isNull();
    }

    @Test
    public void createCompleteWithNoOfferCheckMAxAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.maxAmount).isNull();
    }

    @Test
    public void createCompleteWithNoOfferCheckMinAmount() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.minAmount).isNull();
    }

    @Test
    public void createCompleteWithNoOfferCheckOffers() {
        OfferCollectionResponse response = mapper.offersToCompleteCollectionResponse(new ArrayList<>());

        assertThat(response.offers).isEmpty();
    }
}
