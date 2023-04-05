package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.product.ProductSellRequest;
import ulaval.glo2003.domain.product.SaleStatus;
import ulaval.glo2003.utils.OfferTestUtils;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class UsersSellProductTest extends ApiTest{
    private String productId;
    private String sellerId;

    @BeforeEach
    public void setUp() throws Exception {
        sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
        productId = sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest());
        sellingService.createOffer(ProductTestUtils.USERNAME, productId, OfferTestUtils.createOfferRequest());
    }

    @Test
    public void canUsersSellProduct(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();

        Response response = sellProduct(productId, sellerId, request);

        assertThat(response.getStatus()).isEqualTo(200);
        assertCheckProductStatusIsSold();
    }

    @Test
    public void failUsersSellProductInvalidProductId(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();

        Response response = sellProduct("invalid", sellerId, request);

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failUsersSellProductInvalidSellerId(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();

        Response response = sellProduct(productId, "invalid", request);

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failUsersSellProductInvalidBuyerUsername(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();
        request.username = "invalid";

        Response response = sellProduct(productId, sellerId, request);

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failUsersSellProductMissingSellerId(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();

        Response response = target("/products/{productId}/sell")
                .resolveTemplate("productId", productId)
                .request()
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUsersSellProductMissingBuyerUsername(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();
        request.username = null;

        Response response = sellProduct(productId,sellerId,request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failProductAlreadySold(){
        ProductSellRequest request = ProductTestUtils.createProductSellRequest();
        sellingService.sellProduct(sellerId,productId,request);

        Response response = sellProduct(productId,sellerId,request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("NOT_PERMITTED");
    }




    private void assertCheckProductStatusIsSold(){
        ProductResponse response= sellingService.getProduct(productId);
        System.out.println(response.saleStatus);
        assertThat(response.saleStatus).isEqualTo(SaleStatus.sold.toString());
    }

    private Response sellProduct(String productId, String sellerId, ProductSellRequest request){
        return target("/products/{productId}/sell")
                .resolveTemplate("productId", productId)
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
    }

}
