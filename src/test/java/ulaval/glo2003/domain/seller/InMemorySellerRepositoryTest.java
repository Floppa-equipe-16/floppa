package ulaval.glo2003.domain.seller;

public class InMemorySellerRepositoryTest extends ISellerRepositoryTest {

    @Override
    protected ISellerRepository createRepository() {
        return new InMemorySellerRepository();
    }
}
