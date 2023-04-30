package ulaval.glo2003.infrastructure.mongo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("offers")
public class MongoOffer {

    @Id
    public String id;

    public String productId;
    public String username;
    public Double amount;
    public String message;
    public String createdAt;
    public Boolean selected;
}
