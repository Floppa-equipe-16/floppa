package ulaval.glo2003.domain.product;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;

public class ProductTestUtils {
    public static final String ID = "1";
    public static final String SELLER_ID = "2a74sfs3d2g48";
    public static final String TITLE = "Bob";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_AT = Instant.MAX.toString();
    public static final Double SUGGESTED_PRICE = 200d;

    public static final String CATEGORY = ProductCategory.other.toString();

    public static Product createProduct() {
        return new Product(ID, SELLER_ID, TITLE, CREATE_AT, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
    }

    public static Product createProductStub() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(ID);
        when(product.getSellerId()).thenReturn(SELLER_ID);
        when(product.getCreatedAt()).thenReturn(CREATE_AT);
        when(product.getTitle()).thenReturn(TITLE);
        when(product.getCategory()).thenReturn(CATEGORY);
        when(product.getDescription()).thenReturn(DESCRIPTION);
        when(product.getSuggestedPrice()).thenReturn(SUGGESTED_PRICE);
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
        response.createdAt = CREATE_AT;

        return response;
    }
}
