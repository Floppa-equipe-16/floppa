package ulaval.glo2003.infrastructure.inMemory;

import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.ISellerRepositoryITest;

public class InMemorySellerRepositoryITest extends ISellerRepositoryITest {

    @Override
    protected ISellerRepository createRepository() {
        return new InMemorySellerRepository();
    }
}
