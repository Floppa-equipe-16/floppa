package ulaval.glo2003.domain.offer;

class InMemoryOfferRepositoryTest extends IOfferRepositoryTest {

    @Override
    protected IOfferRepository createRepository() {
        return new InMemoryOfferRepository();
    }
}
