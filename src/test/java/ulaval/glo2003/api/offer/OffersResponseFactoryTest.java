package ulaval.glo2003.api.offer;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.Offer;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

class OffersResponseFactoryTest {
    private static final Double HIGHEST_AMOUNT = 64.50d;
    private static final Double LOWEST_AMOUNT = 35.50d;
    private static final String DESCRIPTION = "This is a description of at least a hundred characters. Let me count them: one, two, three, four, five.";

    private List<Offer> offers;

    @BeforeEach
    protected void setUp() {
        offers = new ArrayList<>();
        offers.add(new Offer("Alice", HIGHEST_AMOUNT, DESCRIPTION));
        offers.add(new Offer("Bob", LOWEST_AMOUNT, DESCRIPTION));
    }

    @Test
    protected void canCreateSummaryResponseWithMultipleOffers() {
        OffersResponse response = OffersResponseFactory.createSummaryResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertDescriptiveFieldsAreNull(response);
    }

    @Test
    protected void createSummaryResponseIsEmptyWithNoOffer() {
        OffersResponse response = OffersResponseFactory.createSummaryResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertDescriptiveFieldsAreNull(response);
    }

    private void assertDescriptiveFieldsAreNull(OffersResponse response) {
        assertThat(response.offers).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.maxAmount).isNull();
    }

    @Test
    protected void canCreateCompleteResponseWithMultipleOffers() {
        OffersResponse response = OffersResponseFactory.createCompleteResponse(offers);

        assertThat(response.count).isEqualTo(2);
        assertThat(response.avgAmount).isEqualTo(50d);
        assertThat(response.maxAmount).isEqualTo(HIGHEST_AMOUNT);
        assertThat(response.minAmount).isEqualTo(LOWEST_AMOUNT);
        assertThat(response.offers).isNotEmpty();
    }

    @Test
    protected void createCompleteResponseIsEmptyWithNoOffer() {
        OffersResponse response = OffersResponseFactory.createCompleteResponse(new ArrayList<>());

        assertThat(response.count).isEqualTo(0);
        assertThat(response.avgAmount).isNull();
        assertThat(response.maxAmount).isNull();
        assertThat(response.minAmount).isNull();
        assertThat(response.offers).isEmpty();
    }
} */
