package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.IOfferRepositoryTest;

public class MongoOfferRepositoryTest extends IOfferRepositoryTest {

    @Override
    protected IOfferRepository createRepository() {
        return new MongoOfferRepository(MongoTestUtils.createLocalDatastore());
    }
}
