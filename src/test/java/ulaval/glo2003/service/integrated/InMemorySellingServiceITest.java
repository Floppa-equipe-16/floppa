package ulaval.glo2003.service.integrated;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.infrastructure.inMemory.InMemoryOfferRepository;
import ulaval.glo2003.infrastructure.inMemory.InMemoryProductRepository;
import ulaval.glo2003.infrastructure.inMemory.InMemorySellerRepository;

public class InMemorySellingServiceITest extends ISellingServiceITest {

    @Override
    protected ISellerRepository createSellerRepository() {
        return new InMemorySellerRepository();
    }

    @Override
    protected IProductRepository createProductRepository() {
        return new InMemoryProductRepository();
    }

    @Override
    protected IOfferRepository createOfferRepository() {
        return new InMemoryOfferRepository();
    }
}
