package ulaval.glo2003.domain.product;

import jakarta.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements IProductRepository {
    private final Map<String, Product> products;

    public InMemoryProductRepository() {
        products = new HashMap<>();
    }

    @Override
    public Product findById(String id) {
        Product product = Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", id)));

        return new Product(product);
    }

    @Override
    public List<Product> findAllProduct() {
        return products.values().stream().map(Product::new).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllBySellerId(String id) {
        return products.values().stream()
                .filter(product -> product.getSellerId().equals(id))
                .map(Product::new)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }
}
