package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class ProductFactoryTest {
    private static final String SELLER_ID = "Sid";
    private static final String TITLE = "Product";
    private static final String DESCRIPTION = "description of the product";
    private static final String CATEGORY = ProductCategory.other.toString();
    private static final Double SUGGESTED_PRICE = 10d;

    private ProductFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new ProductFactory();
    }

    @Test
    public void canCreateProductCheckProductId() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getId()).isNotEmpty();
    }

    @Test
    public void canCreateProductCheckSellerId() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getSellerId()).isEqualTo(SELLER_ID);
    }

    @Test
    public void canCreateProductCheckCreateAt() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getCreatedAt()).isNotEmpty();
    }

    @Test
    public void canCreateProductCheckTitle() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getTitle()).isEqualTo(TITLE);
    }

    @Test
    public void canCreateProductCheckDescription() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    public void canCreateProductCheckSuggestedPrice() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getSuggestedPrice()).isEqualTo(SUGGESTED_PRICE);
    }

    @Test
    public void canCreateProductCheckCategory() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);

        assertThat(product.getCategory()).isEqualTo(CATEGORY);
    }

    @Test
    public void createProductRoundsPrice() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, 10.1234d, CATEGORY);

        assertThat(product.getSuggestedPrice()).isEqualTo(10.12d);
    }

    @Test
    public void createProductThrowsWhenArgsAreEmpty() {
        assertThrows(InvalidParamException.class, () -> factory.createProduct("", "", "", 0d, ""));
    }
}
