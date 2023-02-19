package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductCategory;
import ulaval.glo2003.domain.seller.Seller;

class ProductMapperTest {
    private static final String SELLER_ID = "SELLER";
    private static final String SELLER_NAME = "Bob";

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(SELLER_ID, "Title", "Good description", 10d, ProductCategory.other.toString());
    }

    @Test
    void canMapRequestToProduct() {
        ProductRequest request = createRequest();

        Product product = ProductMapper.requestToProduct(SELLER_ID, request);

        assertThat(product.getTitle()).isEqualTo(request.title);
        assertThat(product.getDescription()).isEqualTo(request.description);
        assertThat(product.getCategory()).isEqualTo(request.category);
        assertThat(product.getSuggestedPrice()).isEqualTo(request.suggestedPrice);
    }

    private ProductRequest createRequest() {
        ProductRequest request = new ProductRequest();
        request.title = "Title";
        request.description = "Good description";
        request.category = ProductCategory.other.toString();
        request.suggestedPrice = 99d;
        return request;
    }

    @Test
    void canMapProductToResponse() {
        ProductResponse response = ProductMapper.productToResponse(product);

        assertFieldsAreEqual(response, product);
        assertThat(response.offers).isNotNull();
        assertThat(response.seller).isNull();
    }

    @Test
    void canMapProductToResponseWithSeller() {
        Seller seller = createSeller();

        ProductResponse response = ProductMapper.productToResponseWithSeller(product, seller);

        assertFieldsAreEqual(response, product);
        assertThat(response.seller).isNotNull();
    }

    private Seller createSeller() {
        return new Seller("Alice", "2000-01-01", "Alice@floppa.com", "14181234567", "My name is Alice!");
    }

    private void assertFieldsAreEqual(ProductResponse response, Product product) {
        assertThat(response.id).isEqualTo(product.getId());
        assertThat(response.createdAt).isEqualTo(product.getCreatedAt());
        assertThat(response.title).isEqualTo(product.getTitle());
        assertThat(response.description).isEqualTo(product.getDescription());
        assertThat(response.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(response.category).isEqualTo(product.getCategory());
    }

    @Test
    void canMapProductsMapToResponsesList() {
        Map<String, Product> products = new HashMap<>();
        products.put("1", product);
        products.put("2", product);

        List<ProductResponse> responses = ProductMapper.productsMapToResponsesList(products);

        assertThat(responses.size()).isEqualTo(2);
    }
}
