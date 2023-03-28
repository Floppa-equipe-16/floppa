package ulaval.glo2003.api.offer;

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
        return amount.equals(request.amount) && message.equals(request.message);
    }
}
