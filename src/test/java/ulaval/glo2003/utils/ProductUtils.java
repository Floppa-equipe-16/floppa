package ulaval.glo2003.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductCategory;
import ulaval.glo2003.domain.product.SaleStatus;

public class ProductUtils {
    public static final String ID = "1";
    public static final String SELLER_ID = "2a74sfs3d2g48";
    public static final String TITLE = "Bob";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_AT = Instant.MAX.toString();
    public static final Double SUGGESTED_PRICE = 15d;
    public static final SaleStatus SALE_STATUS = SaleStatus.ongoing;
    public static final String CATEGORY = ProductCategory.other.toString();

    public static Product createProduct() {
        return new Product(ID, SELLER_ID, TITLE, CREATED_AT, DESCRIPTION, SUGGESTED_PRICE, SALE_STATUS, CATEGORY);
    }

    public static Product createProductStub() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(ID);
        when(product.getSellerId()).thenReturn(SELLER_ID);
        when(product.getCreatedAt()).thenReturn(CREATED_AT);
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getCategory()).thenReturn(CATEGORY);
        when(product.getDescription()).thenReturn(DESCRIPTION);
        when(product.getSuggestedPrice()).thenReturn(SUGGESTED_PRICE);
        when(product.getSaleStatus()).thenReturn(SALE_STATUS);
        return product;
    }

    public static ProductRequest createProductRequest() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = CATEGORY;
        request.description = DESCRIPTION;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequest2() {

        ProductRequest request = new ProductRequest();
        request.title = "Iphone X";
        request.category = ProductCategory.electronics.toString();
        request.description = "old Phone";
        request.suggestedPrice = 1000d;

        return request;
    }

    public static ProductRequest createProductRequest3() {

        ProductRequest request = new ProductRequest();
        request.title = "Iphone 14";
        request.category = ProductCategory.electronics.toString();
        request.description = "new Phone";
        request.suggestedPrice = 1500d;

        return request;
    }

    public static ProductRequest createProductRequest4() {

        ProductRequest request = new ProductRequest();
        request.title = "Prime";
        request.category = ProductCategory.sport.toString();
        request.description = "Vitamine drink";
        request.suggestedPrice = 3.5d;

        return request;
    }

    public static ProductCollectionResponse createProductCollectionResponse() {
        ProductCollectionResponse response = new ProductCollectionResponse();
        response.products = new ArrayList<ProductResponse>();
        response.products.add(createProductResponse());
        return response;
    }

    public static ProductResponse createProductResponse() {

        ProductResponse response = new ProductResponse();
        response.title = TITLE;
        response.description = DESCRIPTION;
        response.category = CATEGORY;
        response.suggestedPrice = SUGGESTED_PRICE;
        response.id = ID;
        response.createdAt = CREATED_AT;
        response.saleStatus = SaleStatus.ongoing.toString();
        response.seller = SellerUtils.createSellerInfo();
        response.seller.id = ID;
        response.seller.name = "bob";
        response.offers = OfferUtils.createOfferCollectionResponse();

        return response;
    }

    public static ProductRequest createProductRequestInvalidTitle() {

        ProductRequest request = new ProductRequest();
        request.title = "";
        request.category = CATEGORY;
        request.description = DESCRIPTION;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestInvalidCategory() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = "???";
        request.description = DESCRIPTION;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestInvalidDescription() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = CATEGORY;
        request.description = "";
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestInvalidSuggestedPrice() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = CATEGORY;
        request.description = DESCRIPTION;
        request.suggestedPrice = -1d;

        return request;
    }

    public static ProductRequest createProductRequestMissingTitle() {

        ProductRequest request = new ProductRequest();
        request.title = null;
        request.category = CATEGORY;
        request.description = DESCRIPTION;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestMissingCategory() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = null;
        request.description = DESCRIPTION;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestMissingDescription() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = CATEGORY;
        request.description = null;
        request.suggestedPrice = SUGGESTED_PRICE;

        return request;
    }

    public static ProductRequest createProductRequestMissingPrice() {

        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.category = CATEGORY;
        request.description = DESCRIPTION;
        request.suggestedPrice = null;

        return request;
    }
}