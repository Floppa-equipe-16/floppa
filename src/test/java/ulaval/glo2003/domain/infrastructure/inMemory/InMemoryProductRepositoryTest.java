package ulaval.glo2003.domain.infrastructure.inMemory;

import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.IProductRepositoryTest;

class InMemoryProductRepositoryTest extends IProductRepositoryTest {
    @Override
    protected IProductRepository createRepository() {
        return new InMemoryProductRepository();
    }
}
