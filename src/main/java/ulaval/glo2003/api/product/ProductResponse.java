package ulaval.glo2003.api.product;

import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.domain.product.Product;

public class ProductResponse {

    protected static class SellerInfo {
        public String id;
        public String name;
    }

    public String title;
    public String description;
    public Double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public SellerInfo seller;
    public OfferCollectionResponse offers;

    public void addSellerInfo(String id, String name) {
        seller = new SellerInfo();
        seller.id = id;
        seller.name = name;
    }

    private boolean isProductResponseEquals(ProductResponse response) {
        return title.equals(response.title)
                && description.equals(response.description)
                && suggestedPrice.equals(response.suggestedPrice)
                && category.equals(response.category)
                && id.equals(response.id)
                && createdAt.equals(response.createdAt);
    }

    private boolean isProductEquals(Product product) {
        return title.equals(product.getTitle())
                && description.equals(product.getDescription())
                && suggestedPrice.equals(product.getSuggestedPrice())
                && category.equals(product.getCategory())
                && id.equals(product.getId())
                && createdAt.equals(product.getCreatedAt());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductResponse) {
            ProductResponse productResponse = ((ProductResponse) o);
            return isProductResponseEquals(productResponse);
        } else if (o instanceof Product) {
            Product product = ((Product) o);
            return isProductEquals(product);
        } else return false;
    }
}
