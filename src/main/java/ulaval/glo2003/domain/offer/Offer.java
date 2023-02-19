package ulaval.glo2003.domain.offer;

import java.time.Instant;
import java.util.UUID;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class Offer {
    private final String id;
    private final String productId;
    private final String username;
    private final Double amount;
    private final String message;
    private final String createdAt;

    public Offer(String productId, String username, Double amount, String message) {
        this.productId = productId;
        this.username = username;
        this.amount = Math.round(amount * 100d) / 100d;
        this.message = message;

        validateParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    private void validateParameters() {
        if (!isMessageLongEnough()) throw new InvalidParamException("message");
    }

    private boolean isMessageLongEnough() {
        return message.length() >= 100;
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

    public String getProductId() {
        return productId;
    }

    public String getId() {
        return id;
    }
}
