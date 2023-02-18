package ulaval.glo2003.domain.seller;

public interface ISellerRepository {
    Seller findById(String id);
    void add(Seller seller);
}
