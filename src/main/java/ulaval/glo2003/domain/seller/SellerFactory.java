package ulaval.glo2003.domain.seller;

import java.time.Instant;
import java.util.UUID;

public class SellerFactory {
    public Seller createSeller(String name, String birthdate, String email, String phoneNumber, String bio) {
        String id = UUID.randomUUID().toString();
        String createdAt = Instant.now().toString();

        Seller seller = new Seller(id, name, createdAt, birthdate, email, phoneNumber, bio);
        SellerValidator.validateParam(seller);
        return seller;
    }
}
