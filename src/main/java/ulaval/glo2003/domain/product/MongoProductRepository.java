package ulaval.glo2003.domain.product;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MongoProductRepository implements IProductRepository {

    private final Datastore datastore;

    public MongoProductRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Product findById(String id) {
        Query<MongoProduct> productsQuery = datastore.find(MongoProduct.class).filter(Filters.eq("id", id));
        List<MongoProduct> products =
                StreamSupport.stream(productsQuery.spliterator(), true).collect(Collectors.toList());

        if (products.size() == 1) {
            MongoProduct mongoProduct = products.get(0);
            return new Product(
                    mongoProduct.id,
                    mongoProduct.sellerId,
                    mongoProduct.title,
                    mongoProduct.createdAt,
                    mongoProduct.description,
                    mongoProduct.suggestedPrice,
                    mongoProduct.category);
        } else {
            throw new NotFoundException(String.format("Product with id '%s' not found", id));
        }
    }

    @Override
    public List<Product> findAll(ProductFilter productFilter) {
        Query<MongoProduct> productsQuery = datastore.find(MongoProduct.class);
        return StreamSupport.stream(productsQuery.spliterator(), true)
                .filter(mongoProduct -> mongoProduct.sellerId.contains(productFilter.getSellerId()))
                .filter(mongoProduct -> mongoProduct
                        .title
                        .toLowerCase()
                        .contains(productFilter.getTitle().toLowerCase()))
                .filter(mongoProduct -> mongoProduct
                        .category
                        .toLowerCase()
                        .contains(productFilter.getCategory().toLowerCase()))
                .filter(mongoProduct -> mongoProduct.suggestedPrice <= productFilter.getMaxPrice())
                .filter(mongoProduct -> mongoProduct.suggestedPrice >= productFilter.getMinPrice())
                .map(mongoProduct -> new Product(
                        mongoProduct.id,
                        mongoProduct.sellerId,
                        mongoProduct.title,
                        mongoProduct.createdAt,
                        mongoProduct.description,
                        mongoProduct.suggestedPrice,
                        mongoProduct.category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllBySellerId(String id) {
        Query<MongoProduct> productsQuery = datastore.find(MongoProduct.class).filter(Filters.eq("sellerId", id));
        return StreamSupport.stream(productsQuery.spliterator(), true)
                .map(mongoProduct -> new Product(
                        mongoProduct.id,
                        mongoProduct.sellerId,
                        mongoProduct.title,
                        mongoProduct.createdAt,
                        mongoProduct.description,
                        mongoProduct.suggestedPrice,
                        mongoProduct.category))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        MongoProduct mongoProduct = new MongoProduct();
        mongoProduct.id = product.getId();
        mongoProduct.sellerId = product.getSellerId();
        mongoProduct.title = product.getTitle();
        mongoProduct.createdAt = product.getCreatedAt();
        mongoProduct.description = product.getDescription();
        mongoProduct.suggestedPrice = product.getSuggestedPrice();
        mongoProduct.category = product.getCategory();

        datastore.save(mongoProduct);
    }

    @Override
    public void reset() {
        datastore.getDatabase().drop();
    }
}
