package ulaval.glo2003.domain.product;

import java.util.List;

public interface IProductRepository {
    Product findById(String id);

    List<Product> findAllBySellerId(String id);

    void add(Product product);
}
