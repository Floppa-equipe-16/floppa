package ulaval.glo2003.api.offer;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.OfferUtils;


public class OfferCollectionResponseTest {

    private OfferCollectionResponse offerCollectionResponse;

    @BeforeEach
    public void setUp() {
        offerCollectionResponse = OfferUtils.createOfferCollectionResponse();
    }

    @Test
    public void CollectionEqualsToHimself() {
        assertThat(offerCollectionResponse).isEqualTo(offerCollectionResponse);
    }

    @Test
    public void CollectionEqualsCollection() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();

        assertThat(offerCollectionResponse).isEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenCountDiff() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();
        newCollection.count = 1;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenMinPriceDiff() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();
        newCollection.minAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenMaxPriceDiff() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();
        newCollection.maxAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenAverageDiff() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();
        newCollection.avgAmount = null;

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }

    @Test
    public void CollectionNotEqualsToCollectionWhenItemsDiff() {
        OfferCollectionResponse newCollection = OfferUtils.createOfferCollectionResponse();
        newCollection.items = new ArrayList<>();

        assertThat(offerCollectionResponse).isNotEqualTo(newCollection);
    }
}
