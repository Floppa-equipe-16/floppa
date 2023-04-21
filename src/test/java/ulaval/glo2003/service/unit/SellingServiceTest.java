package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.service.*;
import ulaval.glo2003.utils.OfferTestUtils;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

class SellingServiceTest {
    private static final String SELLER_ID = "SELLER";
    private static final String PRODUCT_ID = "PRODUCT";

    @Mock
    private NotificationService notificationService = mock(NotificationService.class);

    @Mock
    private ISellerRepository sellerRepositoryMock = mock(ISellerRepository.class);

    @Mock
    private IProductRepository productRepositoryMock = mock(IProductRepository.class);

    @Mock
    private IOfferRepository offerRepositoryMock = mock(IOfferRepository.class);

    @Mock
    private SellerMapper sellerMapperMock = mock(SellerMapper.class);

    @Mock
    private ProductMapper productMapperMock = mock(ProductMapper.class);

    @Mock
    private OfferMapper offerMapperMock = mock(OfferMapper.class);

    @Mock
    private Seller sellerStub;

    @Mock
    private Product productStub;

    @Mock
    private Offer offerStub;

    private SellingService sellingService;

    @BeforeEach
    public void setUp() throws RuntimeException {
        sellingService = new SellingService(
                sellerRepositoryMock,
                productRepositoryMock,
                offerRepositoryMock,
                sellerMapperMock,
                productMapperMock,
                offerMapperMock,
                notificationService);

        sellerStub = createSellerStub();
        productStub = createProductStub();
        offerStub = createOfferStub();
    }

    private Seller createSellerStub() {
        Seller stub = mock(Seller.class);
        when(stub.getId()).thenReturn(SELLER_ID);
        return stub;
    }

    private Product createProductStub() {
        Product stub = mock(Product.class);
        when(stub.getId()).thenReturn(PRODUCT_ID);
        when(stub.getSellerId()).thenReturn(SELLER_ID);
        return stub;
    }

    private Offer createOfferStub() {
        Offer stub = mock(Offer.class);
        when(stub.getId()).thenReturn("OFFER");
        when(stub.getProductId()).thenReturn(PRODUCT_ID);
        return stub;
    }

    @Test
    public void canCreateSeller() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        when(sellerMapperMock.requestToSeller(request)).thenReturn(sellerStub);

        String id = sellingService.createSeller(request);

        assertThat(id).isEqualTo(sellerStub.getId());
        verify(sellerRepositoryMock).save(sellerStub);
    }

    @Test
    public void canGetSellerWithNoProduct() {
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);
        when(productRepositoryMock.findAllBySellerId(SELLER_ID)).thenReturn(Collections.emptyList());
        when(sellerMapperMock.sellerToResponse(sellerStub)).thenReturn(any());

        sellingService.getSeller(SELLER_ID);

        verify(sellerRepositoryMock).findById(SELLER_ID);
        verify(sellerMapperMock).sellerToResponse(sellerStub);
    }

    @Test
    public void canGetSellerWithProduct() {
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);
        when(productRepositoryMock.findAllBySellerId(SELLER_ID)).thenReturn(List.of(productStub));
        when(sellerMapperMock.sellerToResponse(sellerStub)).thenReturn(any());

        sellingService.getSeller(SELLER_ID);

        verify(sellerStub).addProduct(productStub);
        verify(sellerRepositoryMock).findById(SELLER_ID);
        verify(sellerMapperMock).sellerToResponse(sellerStub);
    }

    @Test
    public void canCreateProductWhenSellerExists() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        when(productMapperMock.requestToProduct(SELLER_ID, request)).thenReturn(productStub);
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);

        String id = sellingService.createProduct(SELLER_ID, request);

        assertThat(id).isEqualTo(productStub.getId());
        verify(productRepositoryMock).save(productStub);
        verify(sellerRepositoryMock).findById(SELLER_ID);
    }

    @Test
    public void createProductThrowsWhenSellerDoesNotExist() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        when(productMapperMock.requestToProduct(SELLER_ID, request)).thenReturn(productStub);
        when(sellerRepositoryMock.findById(SELLER_ID)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> sellingService.createProduct(SELLER_ID, request));
    }

    @Test
    public void createProductThrowsWhenSellerIdIsNull() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        when(productMapperMock.requestToProduct(null, request)).thenReturn(productStub);
        when(sellerRepositoryMock.findById(null)).thenThrow(NotFoundException.class);

        assertThrows(MissingParamException.class, () -> sellingService.createProduct(null, request));
    }

    @Test
    public void canGetProductWithNoOffer() {
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);
        when(productRepositoryMock.findById(PRODUCT_ID)).thenReturn(productStub);
        when(productMapperMock.productToResponseWithSeller(productStub, sellerStub))
                .thenReturn(any());

        sellingService.getProduct(PRODUCT_ID);

        verify(productRepositoryMock).findById(PRODUCT_ID);
        verify(offerRepositoryMock).findAllByProductId(PRODUCT_ID);
        verify(sellerRepositoryMock).findById(SELLER_ID);
        verify(productMapperMock).productToResponseWithSeller(productStub, sellerStub);
    }

    @Test
    public void canGetProductWithOffer() {
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);
        when(productRepositoryMock.findById(PRODUCT_ID)).thenReturn(productStub);
        when(offerRepositoryMock.findAllByProductId(PRODUCT_ID)).thenReturn(List.of(offerStub));
        when(productMapperMock.productToResponseWithSeller(productStub, sellerStub))
                .thenReturn(any());

        sellingService.getProduct(PRODUCT_ID);

        verify(productRepositoryMock).findById(PRODUCT_ID);
        verify(offerRepositoryMock).findAllByProductId(PRODUCT_ID);
        verify(productStub).addOffer(offerStub);
        verify(sellerRepositoryMock).findById(SELLER_ID);
        verify(productMapperMock).productToResponseWithSeller(productStub, sellerStub);
    }

    @Test
    public void canGetProducts() {
        ProductFilter filter = ProductTestUtils.createEmptyFilter();
        when(productRepositoryMock.findAll(filter)).thenReturn(List.of(productStub));
        when(offerRepositoryMock.findAllByProductId(PRODUCT_ID)).thenReturn(Collections.emptyList());
        when(sellerRepositoryMock.findById(SELLER_ID)).thenReturn(sellerStub);
        when(productMapperMock.productToResponseWithSeller(productStub, sellerStub))
                .thenReturn(new ProductResponse());

        sellingService.getProducts(filter);

        verify(productRepositoryMock).findAll(filter);
        verify(offerRepositoryMock).findAllByProductId(PRODUCT_ID);
        verify(sellerRepositoryMock).findById(SELLER_ID);
        verify(productMapperMock).productsToCollectionResponse(any());
    }

    @Test
    public void canGetProductsWhenNoProduct() {
        ProductFilter filter = ProductTestUtils.createEmptyFilter();
        when(productRepositoryMock.findAll(filter)).thenReturn(Collections.emptyList());
        when(productMapperMock.productsToCollectionResponse(any())).thenReturn(any());

        sellingService.getProducts(filter);

        verify(productRepositoryMock).findAll(filter);
        verify(productMapperMock).productsToCollectionResponse(Collections.emptyList());
    }

    @Test
    public void canCreateOfferWhenProductExists() {
        OfferRequest request = OfferTestUtils.createOfferRequest();
        String buyerName = "Alice";
        when(offerMapperMock.requestToOffer(PRODUCT_ID, buyerName, request)).thenReturn(offerStub);
        when(productRepositoryMock.findById(PRODUCT_ID)).thenReturn(productStub);
        when(sellerRepositoryMock.findById(any())).thenReturn(sellerStub);
        when(productRepositoryMock.findAllBySellerId(any())).thenReturn(new ArrayList<>());
        when(offerRepositoryMock.findAllByProductId(PRODUCT_ID)).thenReturn(new ArrayList<>());

        String id = sellingService.createOffer(buyerName, PRODUCT_ID, request);

        assertThat(id).isEqualTo(offerStub.getId());
        verify(offerRepositoryMock).save(offerStub);
        verify(productRepositoryMock, times(2)).findById(PRODUCT_ID);
    }

    @Test
    public void createOfferThrowsWhenProductDoesNotExist() {
        OfferRequest request = OfferTestUtils.createOfferRequest();
        String buyerName = "Alice";
        when(offerMapperMock.requestToOffer(PRODUCT_ID, buyerName, request)).thenReturn(offerStub);
        when(productRepositoryMock.findById(PRODUCT_ID)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> sellingService.createOffer(buyerName, PRODUCT_ID, request));
    }

    @Test
    public void createOfferThrowsWhenBuyerNameIsNull() {
        OfferRequest request = OfferTestUtils.createOfferRequest();
        when(offerMapperMock.requestToOffer(PRODUCT_ID, null, request)).thenReturn(offerStub);
        when(productRepositoryMock.findById(PRODUCT_ID)).thenThrow(NotFoundException.class);

        assertThrows(MissingParamException.class, () -> sellingService.createOffer(null, PRODUCT_ID, request));
    }
}
