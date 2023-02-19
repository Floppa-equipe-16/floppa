package ulaval.glo2003.domain.seller;

public interface ISellerRepository {
    Seller findById(String id);

    void save(Seller seller);
}
