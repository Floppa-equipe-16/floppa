package ulaval.glo2003.domain;

import java.time.Instant;
import ulaval.glo2003.api.exceptionHandling.InvalidParamException;

public class Offer {

    private final String username;
    private final Double amount;
    private final String message;
    private final String createdAt;

    public Offer(String username, Double amount, String message) {
        this.username = username;
        this.amount = amount;
        this.message = message;

        validateOfferParameters();

        createdAt = Instant.now().toString();
    }

    private void validateOfferParameters() {
        if (!isMessageLongEnough()) throw new InvalidParamException("message");
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    private boolean isMessageLongEnough() {
        return message.length() >= 100;
    }
}