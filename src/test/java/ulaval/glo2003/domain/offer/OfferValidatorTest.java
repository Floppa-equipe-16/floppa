package ulaval.glo2003.domain.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OfferValidatorTest {

    private OfferValidator offerValidator;

    @Mock
    private Offer offer = mock(Offer.class);

    @Spy
    private OfferValidator offerValidatorSpy;

    @BeforeEach
    void prepareOfferValidator(){
        offerValidator = new OfferValidator(offer);
        offerValidatorSpy = spy(offerValidator);

        setAllValidatorInSpyToValid();
    }

    private void setAllValidatorInSpyToValid(){
        doReturn(false).when(offerValidatorSpy).isMessageTooShort(any());
    }

    @Test
    void testInvalidMessageInValidateParamThrowIfInvalid(){
        doReturn(true).when(offerValidatorSpy).isMessageTooShort(any());

        InvalidParamException thrownInvalidMessage = assertThrows(
                InvalidParamException.class, () ->offerValidatorSpy.validateParamThrowIfInvalid());
        assertThat(thrownInvalidMessage.errorDescription.description).isEqualTo("Invalid parameter 'message'.");
    }

    @Test
    void testIsMessageLongEnough(){
        String messageWith100Char = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
        assertFalse(offerValidator.isMessageTooShort(messageWith100Char));
    }
}
