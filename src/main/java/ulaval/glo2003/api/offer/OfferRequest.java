package ulaval.glo2003.api.offer;

import java.util.Objects;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class OfferRequest {
    public Double amount;
    public String message;

    public void validate() {
        if (amount == null) throw new MissingParamException("amount");
        if (message == null) throw new MissingParamException("message");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferRequest) {
            OfferRequest offerRequest = ((OfferRequest) o);
            return isEqualsTo(offerRequest);
        } else return false;
    }

    private boolean isEqualsTo(OfferRequest request) {
        return Objects.equals(amount, request.amount) && Objects.equals(message, request.message);
    }
}
