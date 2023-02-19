package ulaval.glo2003.domain.offer;

import java.util.List;

public interface IOfferRepository {

    Offer findById(String id);
    List<Offer> findAllByProductId(String productId);
    void add(Offer offer);
}
