package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductFilterTest {

    @Test
    public void canCreateProductFilter() {
        ProductFilter productFilter = new ProductFilter("SELLER", "TITLE", ProductCategory.other.toString(), 10d, 100d);

        assertThat(productFilter.getSellerId()).isEqualTo("SELLER");
        assertThat(productFilter.getTitle()).isEqualTo("TITLE");
        assertThat(productFilter.getCategory()).isEqualTo(ProductCategory.other.toString());
        assertThat(productFilter.getMinPrice()).isEqualTo(10d);
        assertThat(productFilter.getMaxPrice()).isEqualTo(100d);
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenArgsAreNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getSellerId()).isNotNull();
        assertThat(productFilter.getTitle()).isNotNull();
        assertThat(productFilter.getCategory()).isNotNull();
        assertThat(productFilter.getMinPrice()).isNotNull();
        assertThat(productFilter.getMaxPrice()).isNotNull();
    }
}
