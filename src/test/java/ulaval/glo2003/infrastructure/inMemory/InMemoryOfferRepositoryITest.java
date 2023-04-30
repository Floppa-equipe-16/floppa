package ulaval.glo2003.infrastructure.inMemory;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.IOfferRepositoryITest;

class InMemoryOfferRepositoryITest extends IOfferRepositoryITest {

    @Override
    protected IOfferRepository createRepository() {
        return new InMemoryOfferRepository();
    }
}
