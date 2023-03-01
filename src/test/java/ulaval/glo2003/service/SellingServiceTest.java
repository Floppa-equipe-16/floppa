package ulaval.glo2003.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.SellerTestUtils;

import static org.mockito.Mockito.*;

class SellingServiceTest {

    @Mock
    private ISellerRepository sellerRepositoryMock = mock(ISellerRepository.class);
    @Mock
    private IProductRepository productRepositoryMock = mock(IProductRepository.class);
    @Mock
    private IOfferRepository offerRepositoryMock = mock(IOfferRepository.class);
    private SellingService sellingService;
    private SellerRequest request;

    @BeforeEach
    public void setUp() {
        //repositoryManager = new RepositoryManager(sellerRepositoryMock, productRepositoryMock, offerRepositoryMock);

        request = SellerTestUtils.createSellerRequest();
    }

    //Create/get : Seller, Product, Offer

    @Test
    public void canCreateSeller() {
        //Create Seller request


        //repoMan.createSeller()
        //repositoryManager.createSeller(request);

        //assertThat(sellerRepo.gotCalledSave)
        //verify(sellerRepositoryMock).save(null);
    }

}