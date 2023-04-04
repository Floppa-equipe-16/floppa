package ulaval.glo2003.utils.equals;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.utils.ProductUtils;

public class ProductEqualsTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = ProductUtils.createProduct();
    }

    @Test
    public void productRequestEqualsToProduct() {
        ProductRequest request = ProductUtils.createProductRequest();

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isTrue();
    }

    @Test
    public void productRequestNotEqualsToProductWhenTitleDiff() {
        ProductRequest request = ProductUtils.createProductRequest();
        request.title = "new title";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenDescriptionDiff() {
        ProductRequest request = ProductUtils.createProductRequest();
        request.description = "new description";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenCategoryDiff() {
        ProductRequest request = ProductUtils.createProductRequest();
        request.category = "new category";

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productRequestNotEqualsToProductWhenSuggestedPriceDiff() {
        ProductRequest request = ProductUtils.createProductRequest();
        request.suggestedPrice = 1d;

        assertThat(ProductEquals.productRequestEqualsProduct(request, product)).isFalse();
    }

    @Test
    public void productResponseEqualsToProduct() {
        ProductResponse response = ProductUtils.createProductResponse();

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isTrue();
    }

    @Test
    public void productResponseNotEqualsToProductWhenIdDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.id = "new id";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCreateAtDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.createdAt = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenTitleDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.title = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenDescriptionDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.description = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenCategoryDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.category = "???";

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }

    @Test
    public void productResponseNotEqualsToProductWhenSuggestedPriceDiff() {
        ProductResponse response = ProductUtils.createProductResponse();
        response.suggestedPrice = 1d;

        assertThat(ProductEquals.productResponseEqualsProduct(response, product))
                .isFalse();
    }
}
