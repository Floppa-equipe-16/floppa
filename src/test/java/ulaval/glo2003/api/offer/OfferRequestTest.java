package ulaval.glo2003.api.offer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class OfferRequestTest {
    public static final double AMOUNT = 200d;
    public static final String MESSAGE =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi elementum vestibulum turpis sed egestas.";
    private OfferRequest offerRequest;

    @BeforeEach
    public void setUp() {
        offerRequest = new OfferRequest();
        offerRequest.amount = AMOUNT;
        offerRequest.message = MESSAGE;
    }

    @Test
    public void canValidateValidRequest() {
        assertDoesNotThrow(offerRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullAmount() {
        offerRequest.amount = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullMessage() {
        offerRequest.message = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }
}
