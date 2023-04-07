package ulaval.glo2003.domain.infrastructure.mongo;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.IOfferRepositoryITest;
import ulaval.glo2003.utils.MongoTestUtils;

public class MongoOfferRepositoryITest extends IOfferRepositoryITest {

    @Override
    protected IOfferRepository createRepository() {
        return new MongoOfferRepository(MongoTestUtils.createLocalDatastore());
    }
}
