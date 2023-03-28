package ulaval.glo2003.domain.product;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

class ProductFilterTest {

    private static final String SELLER_ID = "SELLER";
    private static final String TITLE = "TITLE";
    private static final String CATEGORY = ProductCategory.other.toString();
    private static final Double MIN_PRICE = 10d;
    private static final Double MAX_PRICE = 100d;

    @Test
    public void canCreateProductFilterCheckSellerId() {
        ProductFilter productFilter = new ProductFilter(SELLER_ID, TITLE, CATEGORY, MIN_PRICE, MAX_PRICE);

        assertThat(productFilter.getSellerId()).isEqualTo(SELLER_ID);
    }

    @Test
    public void canCreateProductFilterCheckTitle() {
        ProductFilter productFilter = new ProductFilter(SELLER_ID, TITLE, CATEGORY, MIN_PRICE, MAX_PRICE);

        assertThat(productFilter.getTitle()).isEqualTo(TITLE);
    }

    @Test
    public void canCreateProductFilterCheckCategory() {
        ProductFilter productFilter = new ProductFilter(SELLER_ID, TITLE, CATEGORY, MIN_PRICE, MAX_PRICE);

        assertThat(productFilter.getCategory()).isEqualTo(CATEGORY);
    }

    @Test
    public void canCreateProductFilterCheckMinPrice() {
        ProductFilter productFilter = new ProductFilter(SELLER_ID, TITLE, CATEGORY, MIN_PRICE, MAX_PRICE);

        assertThat(productFilter.getMinPrice()).isEqualTo(MIN_PRICE);
    }

    @Test
    public void canCreateProductFilterCheckMaxPrice() {
        ProductFilter productFilter = new ProductFilter(SELLER_ID, TITLE, CATEGORY, MIN_PRICE, MAX_PRICE);

        assertThat(productFilter.getMaxPrice()).isEqualTo(MAX_PRICE);
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenSellerIdIsNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getSellerId()).isNotNull();
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenTitleIsNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getTitle()).isNotNull();
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenCategoryIsNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getCategory()).isNotNull();
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenMinPriceIsNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getMinPrice()).isNotNull();
    }

    @Test
    public void createProductFilterHasDefaultValuesWhenMaxPriceIsNull() {
        ProductFilter productFilter = new ProductFilter(null, null, null, null, null);

        assertThat(productFilter.getMaxPrice()).isNotNull();
    }
}
