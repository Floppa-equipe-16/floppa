package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.IProductRepositoryITest;
import ulaval.glo2003.utils.MongoUtils;

public class MongoProductRepositoryITest extends IProductRepositoryITest {

    @Override
    protected IProductRepository createRepository() {
        return new MongoProductRepository(MongoUtils.createLocalDatastore());
    }
}
