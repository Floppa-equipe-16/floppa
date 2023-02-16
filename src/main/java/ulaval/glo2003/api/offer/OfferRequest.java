package ulaval.glo2003.api.offer;

import ulaval.glo2003.domain.exceptions.MissingParamException;

public class OfferRequest {
    public Double amount;
    public String message;

    public void validateOfferNonNullParameter() {
        if (amount == null) throw new MissingParamException("amount");
        if (message == null) throw new MissingParamException("message");
    }
}
