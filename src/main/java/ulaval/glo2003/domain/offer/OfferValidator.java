package ulaval.glo2003.domain.offer;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

class OfferValidator {

    private final Offer offer;

    public OfferValidator(Offer offer) {
        this.offer = offer;
    }

    public void validateParamThrowIfInvalid() {
        if (isMessageTooShort(offer.getMessage())) throw new InvalidParamException("message");
    }

    protected boolean isMessageTooShort(String message) {
        return message.length() < 100;
    }
}
