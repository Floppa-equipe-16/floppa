package ulaval.glo2003.domain.infrastructure.inMemory;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.IOfferRepositoryTest;

class InMemoryOfferRepositoryTest extends IOfferRepositoryTest {

    @Override
    protected IOfferRepository createRepository() {
        return new InMemoryOfferRepository();
    }
}
