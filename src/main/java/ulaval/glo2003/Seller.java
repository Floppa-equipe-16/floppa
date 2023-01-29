package ulaval.glo2003;

import java.time.Instant;
import java.util.UUID;

public class Seller {
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;

    public String id;
    public final String createdAt;

    public Seller() {
        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }
}
