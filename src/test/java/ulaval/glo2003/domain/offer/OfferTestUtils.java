package ulaval.glo2003.domain.offer;

import java.time.Instant;

public class OfferTestUtils {
    private static final String PRODUCT_ID = "10";
    private static final String USERNAME = "Alice";
    private static final double AMOUNT = 200d;
    private static final String MESSAGE = "One item please";
    private static final String CREATION_DATE = Instant.MAX.toString();

    public static Offer createOffer(String id) {
        return new Offer(id, PRODUCT_ID, USERNAME, AMOUNT, MESSAGE, CREATION_DATE);
    }
}
