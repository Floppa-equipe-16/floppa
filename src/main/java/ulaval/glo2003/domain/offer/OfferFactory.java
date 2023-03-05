package ulaval.glo2003.domain.offer;

import java.time.Instant;
import java.util.UUID;

public class OfferFactory {
    private static final double TWO_DECIMAL_ROUNDING_FACTOR = 100d;

    public Offer createOffer(String productId, String username, Double amount, String message) {
        String id = UUID.randomUUID().toString();
        amount = Math.round(amount * TWO_DECIMAL_ROUNDING_FACTOR) / TWO_DECIMAL_ROUNDING_FACTOR;
        String createdAt = Instant.now().toString();

        Offer offer = new Offer(id, productId, username, amount, message, createdAt);
        OfferValidator.validateParam(offer);

        return offer;
    }
}
