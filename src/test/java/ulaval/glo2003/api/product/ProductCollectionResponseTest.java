package ulaval.glo2003.api.product;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.ProductUtils;

public class ProductCollectionResponseTest {

    private ProductCollectionResponse productCollectionResponse;

    @BeforeEach
    public void setUp() {
        productCollectionResponse = ProductUtils.createProductCollectionResponse();
    }

    @Test
    public void collectionEqualsToHimself() {
        assertThat(productCollectionResponse).isEqualTo(productCollectionResponse);
    }

    @Test
    public void collectionEqualsToCollection() {
        ProductCollectionResponse newCollection = ProductUtils.createProductCollectionResponse();

        assertThat(productCollectionResponse).isEqualTo(newCollection);
    }

    @Test
    public void collectionNotEqualsToCollectionWhenListDiff() {
        ProductCollectionResponse newCollection = ProductUtils.createProductCollectionResponse();
        productCollectionResponse.products = new ArrayList<ProductResponse>();

        assertThat(productCollectionResponse).isNotEqualTo(newCollection);
    }
}
