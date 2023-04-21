package ulaval.glo2003.domain.infrastructure.mongo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("sellers")
public class MongoSeller {

    @Id
    public String id;

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;
    public String createdAt;
    public double score;
}
