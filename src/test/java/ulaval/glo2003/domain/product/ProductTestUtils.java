package ulaval.glo2003.domain.product;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;

public class ProductTestUtils {
    public static final String ID = "1";
    public static final String SECOND_ID = "2";
    public static final String SELLER_ID = "SELLER";
    public static final String TITLE = "Bob";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_AT = Instant.MAX.toString();
    public static final Double SUGGESTED_PRICE = 200d;
    public static final Double SECOND_SUGGESTED_PRICE = 25d;
    public static final SaleStatus SALE_STATUS = SaleStatus.ongoing;
    public static final String CATEGORY = ProductCategory.other.toString();

    public static Product createProduct() {
        return new Product(ID, SELLER_ID, TITLE, CREATED_AT, DESCRIPTION, SUGGESTED_PRICE, SALE_STATUS, CATEGORY);
    }

    public static Product createSecondProduct() {
        return new Product(
                SECOND_ID, SELLER_ID, TITLE, CREATED_AT, DESCRIPTION, SECOND_SUGGESTED_PRICE, SALE_STATUS, CATEGORY);
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

    public static ProductResponse createProductResponse() {

        ProductResponse response = new ProductResponse();
        response.title = TITLE;
        response.description = DESCRIPTION;
        response.category = CATEGORY;
        response.suggestedPrice = SUGGESTED_PRICE;
        response.id = ID;
        response.createdAt = CREATED_AT;
        response.saleStatus = SaleStatus.ongoing.toString();

        return response;
    }

    public static ProductFilter createEmptyFilter() {
        return new ProductFilter(null, null, null, null, null);
    }
}
