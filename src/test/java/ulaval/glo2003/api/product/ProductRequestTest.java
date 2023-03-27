package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductTestUtils;

public class ProductRequestTest {
    private ProductRequest productRequest;

    @BeforeEach
    public void setUp() {
        productRequest = ProductTestUtils.createProductRequest();
    }

    @Test
    public void canValidateValidRequest() {
        assertDoesNotThrow(productRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullTitle() {
        productRequest.title = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullDescription() {
        productRequest.description = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullSuggestedPrice() {
        productRequest.suggestedPrice = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullCategory() {
        productRequest.category = null;

        assertThrows(MissingParamException.class, productRequest::validate);
    }

    @Test
    public void productRequestEqualsToHimSelf(){
        assertThat(productRequest).isEqualTo(productRequest);
    }

    @Test
    public void productRequestEqualsToProductRequest(){
        ProductRequest newProductRequest = ProductTestUtils.createProductRequest();

        assertThat(productRequest).isEqualTo(newProductRequest);
    }

    @Test
    public void productRequestNotEqualsToProductRequest(){
        ProductRequest newProductRequest = ProductTestUtils.createProductRequest();
        newProductRequest.title = "new title";

        assertThat(productRequest).isNotEqualTo(newProductRequest);
    }

    @Test
    public void productRequestEqualsToProduct(){
        Product product = ProductTestUtils.createProduct();

        assertThat(productRequest).isEqualTo(product);
    }

    @Test
    public void productRequestNotEqualsToProduct(){
        Product product = ProductTestUtils.createProduct();
        productRequest.title = "new title";

        assertThat(productRequest).isNotEqualTo(product);
    }
}
