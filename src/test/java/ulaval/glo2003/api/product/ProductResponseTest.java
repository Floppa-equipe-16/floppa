package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.utils.ProductUtils;

public class ProductResponseTest {

    private ProductResponse productResponse;

    @BeforeEach
    public void setUp() {
        productResponse = ProductUtils.createProductResponse();
    }

    @Test
    public void productResponseEqualsToHimSelf() {
        assertThat(productResponse).isEqualTo(productResponse);
    }

    @Test
    public void productResponseEqualsToProductResponse() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();

        assertThat(productResponse).isEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenIdDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.id = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenCreateAtDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.createdAt = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenTitleDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.title = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenCategoryDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.category = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenDescriptionDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.description = "???";

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenSuggestedPriceDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.suggestedPrice = 1d;

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenSellerDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.seller = new ProductResponse.SellerInfo();

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }

    @Test
    public void productResponseNotEqualsToProductResponseWhenOffersDiff() {
        ProductResponse newProductResponse = ProductUtils.createProductResponse();
        productResponse.offers = new OfferCollectionResponse();

        assertThat(productResponse).isNotEqualTo(newProductResponse);
    }
}
