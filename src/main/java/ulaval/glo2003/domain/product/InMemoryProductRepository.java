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
        return Optional.ofNullable(products.get(id))
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", id)));
    }

    @Override
    public List<Product> findAllBySellerId(String id) {
        return products.values().stream().filter(product -> product.getSellerId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }
}
