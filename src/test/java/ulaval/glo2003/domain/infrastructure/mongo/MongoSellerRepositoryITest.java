package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.ISellerRepositoryITest;

public class MongoSellerRepositoryITest extends ISellerRepositoryITest {

    @Override
    protected ISellerRepository createRepository() {
        return new MongoSellerRepository(MongoTestUtils.createLocalDatastore());
    }
}
