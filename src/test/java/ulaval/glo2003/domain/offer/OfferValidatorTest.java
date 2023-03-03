package ulaval.glo2003.domain.offer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class OfferValidatorTest {
    @Test
    public void validateThrowsWithInvalidMessage() {
        Offer offerStub = mock(Offer.class);

        try (MockedStatic<OfferValidator> offerValidatorMockedStatic =
                Mockito.mockStatic(OfferValidator.class, Mockito.CALLS_REAL_METHODS)) {
            offerValidatorMockedStatic
                    .when(() -> OfferValidator.isMessageTooShort(any()))
                    .thenReturn(true);

            InvalidParamException thrownInvalidMessage =
                    assertThrows(InvalidParamException.class, () -> OfferValidator.validateParam(offerStub));

            assertThat(thrownInvalidMessage.errorDescription.description)
                    .ignoringCase()
                    .contains("message");
        }
    }

    @Test
    public void canCheckIsMessageTooShort() {
        String messageWith99Char =
                "0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-0123456789-";

        assertThat(OfferValidator.isMessageTooShort(messageWith99Char)).isTrue();
    }
}
