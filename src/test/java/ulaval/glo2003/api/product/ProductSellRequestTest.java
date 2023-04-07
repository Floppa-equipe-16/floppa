package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.utils.ProductTestUtils;

public class ProductSellRequestTest {
    private ProductSellRequest productSellRequest;

    @BeforeEach
    public void setUp() {
        productSellRequest = ProductTestUtils.createProductSellRequest();
    }

    @Test
    public void canValidateValidRequest() {
        assertDoesNotThrow(productSellRequest::validate);
    }

    @Test
    public void validateThrowsWhenNullUsername() {
        productSellRequest.username = null;

        assertThrows(MissingParamException.class, productSellRequest::validate);
    }

    @Test
    public void productSellRequestEqualsToProductRequest() {
        ProductSellRequest newProductSellRequest = ProductTestUtils.createProductSellRequest();

        assertThat(productSellRequest).isEqualTo(newProductSellRequest);
    }

    @Test
    public void productSellRequestNotEqualsToProductRequestWhenUsernameDiff() {
        ProductSellRequest newProductSellRequest = ProductTestUtils.createProductSellRequest();
        newProductSellRequest.username = "newUsername";

        assertThat(productSellRequest).isNotEqualTo(newProductSellRequest);
    }
}
