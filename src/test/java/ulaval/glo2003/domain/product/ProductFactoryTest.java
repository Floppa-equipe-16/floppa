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

    private ProductFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new ProductFactory();
    }

    @Test
    public void canCreateProduct() {
        Product product = factory.createProduct(SELLER_ID, TITLE, DESCRIPTION, 10d, CATEGORY);

        assertThat(product.getId()).isNotEmpty();
        assertThat(product.getSellerId()).isEqualTo(SELLER_ID);
        assertThat(product.getCreatedAt()).isNotEmpty();
        assertThat(product.getTitle()).isEqualTo(TITLE);
        assertThat(product.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(product.getSuggestedPrice()).isEqualTo(10d);
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
