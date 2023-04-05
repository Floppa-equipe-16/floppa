package ulaval.glo2003.api.offer;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.OfferTestUtils;

public class OfferCollectionResponseTest {

    private OfferCollectionResponse offerCollectionResponse;

    @BeforeEach
    public void setUp() {
        offerCollectionResponse = OfferTestUtils.createOfferCollectionResponse();
    }

    @Test
    public void CollectionEqualsToHimself() {
        assertThat(offerCollectionResponse).isEqualTo(offerCollectionResponse);
    }

    @Test
    public void CollectionEqualsCollection() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();

        assertThat(offerCollectionResponse).isEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenCountDiff() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();
        newCollection.count = 1;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenMinPriceDiff() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();
        newCollection.minAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenMaxPriceDiff() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();
        newCollection.maxAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenAverageDiff() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();
        newCollection.avgAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenItemsDiff() {
        OfferCollectionResponse newCollection = OfferTestUtils.createOfferCollectionResponse();
        newCollection.items = new ArrayList<>();

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }
}
