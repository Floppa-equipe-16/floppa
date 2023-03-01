package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class OfferTest {

    private Offer offer;

    @Mock
    Offer offerMock = mock(Offer.class);

    @BeforeEach
    public void setUp() {
        offer = OfferTestUtils.createOffer("1");
    }

    @Test
    public void canCopyOffer() {
        Offer offerCopy = new Offer(offer);

        assertThat(offerCopy).isEqualTo(offer);
    }

    @Test
    public void canCompareIdenticalOffers() {
        doReturn(offer.getId()).when(offerMock).getId();
        doReturn(offer.getProductId()).when(offerMock).getProductId();
        doReturn(offer.getUsername()).when(offerMock).getUsername();
        doReturn(offer.getAmount()).when(offerMock).getAmount();
        doReturn(offer.getCreatedAt()).when(offerMock).getCreatedAt();
        doReturn(offer.getMessage()).when(offerMock).getMessage();

        assertThat(offer).isEqualTo(offerMock);
    }

    @Test
    public void canCompareDifferentOffers() {
        doReturn(offer.getId()).when(offerMock).getId();
        doReturn(offer.getProductId()).when(offerMock).getProductId();
        doReturn("Different username").when(offerMock).getUsername();
        doReturn(offer.getAmount()).when(offerMock).getAmount();
        doReturn(offer.getCreatedAt()).when(offerMock).getCreatedAt();
        doReturn(offer.getMessage()).when(offerMock).getMessage();

        assertThat(offer).isNotEqualTo(offerMock);
    }
}
