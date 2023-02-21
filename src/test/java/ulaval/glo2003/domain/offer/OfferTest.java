package ulaval.glo2003.domain.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class OfferTest {

    private Offer offer;

    @Mock
    Offer offerMock = mock(Offer.class);

    @BeforeEach
    void prepareOffer(){
        String productId = "valid ID";
        String userName = "Bob";
        Double amount = 19.5d;
        String message100Char = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        offer = new Offer(productId,userName,amount,message100Char);
    }

    @Test
    void TestCopyConstructor(){
        Offer offerCopy = new Offer(offer);

        assertThat(offerCopy).isEqualTo(offer);
    }

    @Test
    void testEqualFunction(){
        doReturn(offer.getProductId()).when(offerMock).getProductId();
        doReturn(offer.getUsername()).when(offerMock).getUsername();
        doReturn(offer.getId()).when(offerMock).getId();
        doReturn(offer.getAmount()).when(offerMock).getAmount();
        doReturn(offer.getCreatedAt()).when(offerMock).getCreatedAt();
        doReturn(offer.getMessage()).when(offerMock).getMessage();


        assertThat(offer).isEqualTo(offerMock);
    }

}
