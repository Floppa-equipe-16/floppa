package ulaval.glo2003.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.exceptionHandling.MissingParamException;

public class ProductRequestTest {

    private ProductRequest productRequest;
    private String nonNullTitle;
    private String nonNullDescription;
    private Double nonNullSuggestedPrice;
    private String nonNullCategory;

    @BeforeEach
    public void prepareNonNullParameters() {
        nonNullTitle = "Iphone XR";
        nonNullDescription = "A relatively new Iphone working as good as a new one";
        nonNullSuggestedPrice = 200d;
        nonNullCategory = "electronics";
    }

    @BeforeEach
    public void preparevalidProductRequest() {
        productRequest = new ProductRequest();
        productRequest.title = nonNullTitle;
        productRequest.description = nonNullDescription;
        productRequest.suggestedPrice = nonNullSuggestedPrice;
        productRequest.category = nonNullCategory;
    }

    @Test
    public void validateMethodThrowsWhenNullTitle() {
        productRequest.title = null;

        assertThrows(MissingParamException.class, () -> productRequest.validateProductNonNullParameter());
    }

    @Test
    public void validateMethodThrowsWhenNullDescription() {
        productRequest.description = null;

        assertThrows(MissingParamException.class, () -> productRequest.validateProductNonNullParameter());
    }

    @Test
    public void validateMethodThrowsWhenNullSuggestedPrice() {
        productRequest.suggestedPrice = null;

        assertThrows(MissingParamException.class, () -> productRequest.validateProductNonNullParameter());
    }

    @Test
    public void validateMethodThrowsWhenNullCategory() {
        productRequest.category = null;

        assertThrows(MissingParamException.class, () -> productRequest.validateProductNonNullParameter());
    }
}
