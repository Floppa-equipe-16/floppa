package ulaval.glo2003.domain.infrastructure.inMemory;

import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.ISellerRepositoryTest;

public class InMemorySellerRepositoryTest extends ISellerRepositoryTest {

    @Override
    protected ISellerRepository createRepository() {
        return new InMemorySellerRepository();
    }
}
