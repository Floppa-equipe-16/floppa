package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.IProductRepositoryTest;

public class MongoProductRepositoryTest extends IProductRepositoryTest {

    @Override
    protected IProductRepository createRepository() {
        return new MongoProductRepository(MongoTestUtils.createLocalDatastore());
    }
}
