package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.utils.ProductTestUtils;

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
    public void productResponseNotEqualsToProductResponseWhenIdDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.id = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenCreateAtDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.createdAt = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenTitleDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.title = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenCategoryDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.category = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenDescriptionDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.description = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenSuggestedPriceDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.suggestedPrice = 1d;

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenSellerDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.seller = new ProductResponse.SellerInfo();

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenOffersDiff() {
        ProductResponse newProductResponse = ProductTestUtils.createProductResponse();
        productResponse.offers = new OfferCollectionResponse();

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }
}
