package ulaval.glo2003.service;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.InMemoryOfferRepository;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.InMemoryProductRepository;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.InMemorySellerRepository;

public class RepositoryManagerFactory {

    public RepositoryManager create() {
        ISellerRepository sellerRepository = new InMemorySellerRepository();
        IProductRepository productRepository = new InMemoryProductRepository();
        IOfferRepository offerRepository = new InMemoryOfferRepository();
        return new RepositoryManager(sellerRepository, productRepository, offerRepository);
    }
}
