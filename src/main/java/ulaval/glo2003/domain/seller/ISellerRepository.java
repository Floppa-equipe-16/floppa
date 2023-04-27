package ulaval.glo2003.domain.seller;

import java.util.List;

public interface ISellerRepository {
    Seller findById(String id);

    List<Seller> findTopRanked(Integer amount);

    void save(Seller seller);

    void reset();
}
