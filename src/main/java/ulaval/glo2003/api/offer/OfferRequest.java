package ulaval.glo2003.api.offer;

import ulaval.glo2003.domain.exceptions.MissingParamException;

public class OfferRequest {
    public Double amount;
    public String message;

    public void validate() {
        if (amount == null) throw new MissingParamException("amount");
        if (message == null) throw new MissingParamException("message");
    }

    private boolean isOfferRequestEquals(OfferRequest request) {
        return amount.equals(request.amount) && message.equals(request.message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferRequest) {
            OfferRequest offerRequest = ((OfferRequest) o);
            return isOfferRequestEquals(offerRequest);
        } else return false;
    }
}
