package ulaval.glo2003.infrastructure.mongo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.domain.product.SaleStatus;

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
            return deserialize(mongoProduct);
        } else {
            throw new NotFoundException(String.format("Product with id '%s' not found", id));
        }
    }

    @Override
    public List<Product> findAll(ProductFilter productFilter) {
        Query<MongoProduct> productsQuery = datastore
                .find(MongoProduct.class)
                .filter(
                        Filters.eq("sellerId", Pattern.compile(productFilter.getSellerId())),
                        Filters.eq("title", Pattern.compile(productFilter.getTitle(), Pattern.CASE_INSENSITIVE)),
                        Filters.eq("category", Pattern.compile(productFilter.getCategory(), Pattern.CASE_INSENSITIVE)),
                        Filters.lte("suggestedPrice", productFilter.getMaxPrice()),
                        Filters.gte("suggestedPrice", productFilter.getMinPrice()));
        return StreamSupport.stream(productsQuery.spliterator(), true)
                .map(this::deserialize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllBySellerId(String id) {
        Query<MongoProduct> productsQuery = datastore.find(MongoProduct.class).filter(Filters.eq("sellerId", id));
        return StreamSupport.stream(productsQuery.spliterator(), true)
                .map(this::deserialize)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product) {
        datastore.save(serialize(product));
    }

    @Override
    public void reset() {
        datastore.getDatabase().drop();
    }

    private MongoProduct serialize(Product product) {
        MongoProduct mongoProduct = new MongoProduct();
        mongoProduct.id = product.getId();
        mongoProduct.sellerId = product.getSellerId();
        mongoProduct.title = product.getTitle();
        mongoProduct.createdAt = product.getCreatedAt();
        mongoProduct.description = product.getDescription();
        mongoProduct.suggestedPrice = product.getSuggestedPrice();
        mongoProduct.saleStatus = product.getSaleStatus().toString();
        mongoProduct.category = product.getCategory();
        return mongoProduct;
    }

    private Product deserialize(MongoProduct mongoProduct) {
        return new Product(
                mongoProduct.id,
                mongoProduct.sellerId,
                mongoProduct.title,
                mongoProduct.createdAt,
                mongoProduct.description,
                mongoProduct.suggestedPrice,
                SaleStatus.valueOf(mongoProduct.saleStatus),
                mongoProduct.category);
    }
}
