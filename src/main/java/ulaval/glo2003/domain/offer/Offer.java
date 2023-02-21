package ulaval.glo2003.domain.offer;

import java.time.Instant;
import java.util.UUID;

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

        OfferValidator offerValidator = new OfferValidator(this);
        offerValidator.validateParamThrowIfInvalid();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    public Offer(Offer that) {
        productId = that.getProductId();
        username = that.getUsername();
        amount = that.getAmount();
        message = that.getMessage();
        id = that.getId();
        createdAt = that.getCreatedAt();
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Offer) {
            Offer that = (Offer) obj;

            return id.equalsIgnoreCase(that.getId())
                    && productId.equalsIgnoreCase(that.getProductId())
                    && username.equalsIgnoreCase(that.getUsername())
                    && message.equalsIgnoreCase(that.getMessage())
                    && amount.equals(that.getAmount())
                    && createdAt.equalsIgnoreCase(that.getCreatedAt());
        }
        return false;
    }
}
