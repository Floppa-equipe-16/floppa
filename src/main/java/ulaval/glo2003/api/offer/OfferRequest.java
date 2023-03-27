package ulaval.glo2003.api.offer;

import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.offer.Offer;

public class OfferRequest {
    public Double amount;
    public String message;

    public void validate() {
        if (amount == null) throw new MissingParamException("amount");
        if (message == null) throw new MissingParamException("message");
    }

    private boolean isOfferRequestEquals(OfferRequest request){
        return amount.equals(request.amount) &&
                message.equals(request.message);
    }

    private boolean isOfferEquals(Offer offer){
        return amount.equals(offer.getAmount()) &&
                message.equals(offer.getMessage());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof OfferRequest){
            OfferRequest offerRequest = ((OfferRequest) o);
            return isOfferRequestEquals(offerRequest);
        }
        else if (o instanceof Offer){
            Offer offer = ((Offer) o);
            return isOfferEquals(offer);
        }else return false;
    }
}
