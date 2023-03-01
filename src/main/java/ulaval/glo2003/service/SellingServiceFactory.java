package ulaval.glo2003.service;

import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.InMemoryOfferRepository;
import ulaval.glo2003.domain.offer.OfferFactory;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.InMemoryProductRepository;
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.InMemorySellerRepository;
import ulaval.glo2003.domain.seller.SellerFactory;

public class SellingServiceFactory {

    public SellingService create() {
        ISellerRepository sellerRepository = new InMemorySellerRepository();
        IProductRepository productRepository = new InMemoryProductRepository();
        IOfferRepository offerRepository = new InMemoryOfferRepository();

        SellerFactory sellerFactory = new SellerFactory();
        ProductFactory productFactory = new ProductFactory();
        OfferFactory offerFactory = new OfferFactory();

        OfferMapper offerMapper = new OfferMapper(offerFactory);
        ProductMapper productMapper = new ProductMapper(productFactory, offerMapper);
        SellerMapper sellerMapper = new SellerMapper(sellerFactory, productMapper);

        return new SellingService(sellerRepository, productRepository, offerRepository, sellerMapper, productMapper, offerMapper);
    }
}
