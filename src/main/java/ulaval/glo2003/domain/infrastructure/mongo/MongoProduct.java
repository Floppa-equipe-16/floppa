package ulaval.glo2003.domain.infrastructure.mongo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("products")
public class MongoProduct {

    @Id
    public String id;

    public String sellerId;
    public String title;
    public String createdAt;
    public String description;
    public Double suggestedPrice;
    public String category;
}
