package ulaval.glo2003.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class ProductRequestTest {

    private final ProductRequest productRequest = new ProductRequest();

    @BeforeEach
    public void prepareProductRequest() {
        productRequest.title = "Iphone XR";
        productRequest.description = "A relatively new Iphone working as good as a new one";
        productRequest.suggestedPrice = 200d;
        productRequest.category = "electronics";
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
