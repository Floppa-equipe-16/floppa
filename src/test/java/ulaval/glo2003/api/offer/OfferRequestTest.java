package ulaval.glo2003.api.offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfferRequestTest {
    public static final double AMOUNT = 200d;
    public static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi elementum vestibulum turpis sed egestas.";
    private final OfferRequest offerRequest = new OfferRequest();

    @BeforeEach
    public void prepareProductRequest() {
        offerRequest.amount = AMOUNT;
        offerRequest.message = MESSAGE;
    }
    @Test
    public void validateMethodThrowsWhenNullAmount() {
        offerRequest.amount = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }
    @Test
    public void validateMethodThrowsWhenNullMessage() {
        offerRequest.message = null;

        assertThrows(MissingParamException.class, offerRequest::validate);
    }
}
