package ulaval.glo2003.service.integrated;

import dev.morphia.Datastore;
import ulaval.glo2003.domain.infrastructure.mongo.MongoOfferRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoProductRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoSellerRepository;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.utils.MongoTestUtils;

public class MongoSellingServiceITest extends ISellingServiceITest {

    Datastore dataStore = MongoTestUtils.createLocalDatastore();

    @Override
    protected ISellerRepository createSellerRepository() {
        return new MongoSellerRepository(dataStore);
    }

    @Override
    protected IProductRepository createProductRepository() {
        return new MongoProductRepository(dataStore);
    }

    @Override
    protected IOfferRepository createOfferRepository() {
        return new MongoOfferRepository(dataStore);
    }
}
