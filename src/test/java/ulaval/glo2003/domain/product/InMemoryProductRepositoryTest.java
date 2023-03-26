package ulaval.glo2003.domain.product;

class InMemoryProductRepositoryTest extends IProductRepositoryTest {
    @Override
    protected IProductRepository createRepository() {
        return new InMemoryProductRepository();
    }
}
