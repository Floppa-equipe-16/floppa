package ulaval.glo2003.utils.equals;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.utils.ProductTestUtils;

public class ProductEqualsTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = ProductTestUtils.createProduct();
    }

    @Test
    public void productRequestEqualsToProduct() {
        ProductRequest request = ProductTestUtils.createProductRequest();

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isTrue();
    }

    @Test
    public void productRequestNotEqualsToProductWhenTitleDiff() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        request.title = "new title";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenDescriptionDiff() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        request.description = "new description";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenCategoryDiff() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        request.category = "new category";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenSuggestedPriceDiff() {
        ProductRequest request = ProductTestUtils.createProductRequest();
        request.suggestedPrice = 1d;

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productResponseEqualsToProduct() {
        ProductResponse response = ProductTestUtils.createProductResponse();

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isTrue();
    }

    @Test
    public void productResponseNotEqualsToProductWhenIdDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.id = "new id";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCreateAtDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.createdAt = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenTitleDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.title = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenDescriptionDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.description = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCategoryDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.category = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenSuggestedPriceDiff() {
        ProductResponse response = ProductTestUtils.createProductResponse();
        response.suggestedPrice = 1d;

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }
}
