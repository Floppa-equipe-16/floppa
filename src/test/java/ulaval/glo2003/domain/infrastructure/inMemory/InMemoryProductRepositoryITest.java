package ulaval.glo2003.domain.infrastructure.inMemory;

import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.IProductRepositoryITest;

class InMemoryProductRepositoryITest extends IProductRepositoryITest {
    @Override
    protected IProductRepository createRepository() {
        return new InMemoryProductRepository();
    }
}
