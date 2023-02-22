package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class OfferValidatorTest {

    @Mock
    private Offer offerMock = mock(Offer.class);

    @Test
    void validateWithInvalidMessage() {
        try (MockedStatic<OfferValidator> offerValidatorMockedStatic =
                Mockito.mockStatic(OfferValidator.class, Mockito.CALLS_REAL_METHODS)) {
            offerValidatorMockedStatic
                    .when(() -> OfferValidator.isMessageTooShort(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidMessage =
                    assertThrows(InvalidParamException.class, () -> OfferValidator.validateParam(offerMock));

            assertThat(thrownInvalidMessage.errorDescription.description).isEqualTo("Invalid parameter 'message'.");
        }
    }

    @Test
    void IsMessageTooShort() {
        String messageWith99Char =
                "0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-";

        assertThat(OfferValidator.isMessageTooShort(messageWith99Char)).isTrue();
    }
}
