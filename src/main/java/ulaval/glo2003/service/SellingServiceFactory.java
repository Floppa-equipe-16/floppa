package ulaval.glo2003.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.concurrent.TimeUnit;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.MongoOfferRepository;
import ulaval.glo2003.domain.offer.OfferFactory;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.MongoProductRepository;
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.MongoSellerRepository;
import ulaval.glo2003.domain.seller.SellerFactory;

public class SellingServiceFactory {

    private final String FLOPPA_MONGO_CLUSTER_URL =
            "mongodb+srv://root:ulaval@floppa-staging.sasi1f4.mongodb.net/?retryWrites=true&w=majority";
    private final String FLOPPA_MONGO_DATABASE = "floppa-staging";

    public SellingService create() {
        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.serverSelectionTimeout(5000, TimeUnit.MILLISECONDS))
                .applyToConnectionPoolSettings(builder -> builder.maxConnectionIdleTime(5000, TimeUnit.MILLISECONDS))
                .applyConnectionString(new ConnectionString(FLOPPA_MONGO_CLUSTER_URL))
                .build());
        healthChek(client);
        Datastore datastore = Morphia.createDatastore(client, FLOPPA_MONGO_DATABASE);
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();

        ISellerRepository sellerRepository = new MongoSellerRepository(datastore);
        IProductRepository productRepository = new MongoProductRepository(datastore);
        IOfferRepository offerRepository = new MongoOfferRepository(datastore);

        SellerFactory sellerFactory = new SellerFactory();
        ProductFactory productFactory = new ProductFactory();
        OfferFactory offerFactory = new OfferFactory();

        OfferMapper offerMapper = new OfferMapper(offerFactory);
        ProductMapper productMapper = new ProductMapper(productFactory, offerMapper);
        SellerMapper sellerMapper = new SellerMapper(sellerFactory, productMapper);

        return new SellingService(
                sellerRepository, productRepository, offerRepository, sellerMapper, productMapper, offerMapper);
    }

    private void healthChek(MongoClient client) {
        client.listDatabaseNames().first();
    }
}
