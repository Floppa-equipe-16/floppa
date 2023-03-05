package ulaval.glo2003.domain.offer;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

class OfferValidator {

    private static final int MINIMUM_MESSAGE_LENGTH = 100;

    public static void validateParam(Offer offer) {
        if (isMessageTooShort(offer.getMessage())) throw new InvalidParamException("message");
    }

    public static boolean isMessageTooShort(String message) {
        return message.length() < MINIMUM_MESSAGE_LENGTH;
    }
}
