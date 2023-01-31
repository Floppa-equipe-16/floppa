package ulaval.glo2003;

import java.time.Instant;
import java.util.UUID;

public class Product {
    public String title;
    public String description;
    public Number suggestedPrice;

    public String category;

    public String id;
    public final String createdAt;

    public Product() {
        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }
}
