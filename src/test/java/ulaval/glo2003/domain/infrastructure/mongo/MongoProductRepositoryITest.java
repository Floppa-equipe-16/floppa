package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.IProductRepositoryITest;

public class MongoProductRepositoryITest extends IProductRepositoryITest {

    @Override
    protected IProductRepository createRepository() {
        return new MongoProductRepository(MongoTestUtils.createLocalDatastore());
    }
}
