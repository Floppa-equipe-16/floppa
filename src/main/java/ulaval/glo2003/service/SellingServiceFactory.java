package ulaval.glo2003.service;

import dev.morphia.Datastore;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.infrastructure.inMemory.InMemoryOfferRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoOfferRepository;
import ulaval.glo2003.domain.offer.OfferFactory;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.infrastructure.inMemory.InMemoryProductRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoProductRepository;
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.infrastructure.inMemory.InMemorySellerRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoSellerRepository;
import ulaval.glo2003.domain.seller.SellerFactory;

public class SellingServiceFactory {

    public SellingService create() {
        ISellerRepository sellerRepository = new InMemorySellerRepository();
        IProductRepository productRepository = new InMemoryProductRepository();
        IOfferRepository offerRepository = new InMemoryOfferRepository();

        return create(sellerRepository, productRepository, offerRepository);
    }

    public SellingService create(Datastore datastore) {
        ISellerRepository sellerRepository = new MongoSellerRepository(datastore);
        IProductRepository productRepository = new MongoProductRepository(datastore);
        IOfferRepository offerRepository = new MongoOfferRepository(datastore);

        return create(sellerRepository, productRepository, offerRepository);
    }

    private SellingService create(
            ISellerRepository sellerRepository,
            IProductRepository productRepository,
            IOfferRepository offerRepository) {
        SellerFactory sellerFactory = new SellerFactory();
        ProductFactory productFactory = new ProductFactory();
        OfferFactory offerFactory = new OfferFactory();

        OfferMapper offerMapper = new OfferMapper(offerFactory);
        ProductMapper productMapper = new ProductMapper(productFactory, offerMapper);
        SellerMapper sellerMapper = new SellerMapper(sellerFactory, productMapper);

        return new SellingService(
                sellerRepository, productRepository, offerRepository, sellerMapper, productMapper, offerMapper);
    }
}
