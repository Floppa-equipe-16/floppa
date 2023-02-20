package ulaval.glo2003.api.product;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class ProductRequestTest {
    public static final String TITLE = "Iphone XR";
    public static final String DESCRIPTION = "A relatively new Iphone working as good as a new one";
    public static final double SUGGESTED_PRICE = 200d;
    public static final String CATEGORY = "electronics";
    private final ProductRequest productRequest = new ProductRequest();

    @BeforeEach
    public void prepareProductRequest() {
        productRequest.title = TITLE;
        productRequest.description = DESCRIPTION;
        productRequest.suggestedPrice = SUGGESTED_PRICE;
        productRequest.category = CATEGORY;
    }

    @Test
    public void validateMethodThrowsWhenNullTitle() {
        productRequest.title = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateMethodThrowsWhenNullDescription() {
        productRequest.description = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateMethodThrowsWhenNullSuggestedPrice() {
        productRequest.suggestedPrice = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateMethodThrowsWhenNullCategory() {
        productRequest.category = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }
}
