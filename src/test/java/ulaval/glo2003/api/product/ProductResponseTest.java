package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductTestUtils;

public class ProductResponseTest {

    private ProductResponse productResponse;

    @BeforeEach
    public void setUp() {
        productResponse = ProductTestUtils.createProductResponse();
    }

    @Test
    public void productResponseEqualsToHimSelf() {
        assertThat(productResponse).isEqualTo(productResponse);
    }

    @Test
    public void productResponseEqualsToProductResponse() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();

        assertThat(productResponse).isEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponse() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.id = "new id";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseEqualsToProduct() {
        Product product = ProductTestUtils.createProduct();

        assertThat(productResponse).isEqualTo(product);
    }

    @Test
    public void productResponseNotEqualsToProduct() {
        Product product = ProductTestUtils.createProduct();
        productResponse.id = "new id";

        assertThat(productResponse).isNotEqualTo(product);
    }
}
