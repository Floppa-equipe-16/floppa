package ulaval.glo2003.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.domain.offer.Offer;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    public String saleStatus;

    public SellerInfo seller;
    public OfferCollectionResponse offers;
    public Offer selectedOffer;

    public void addSellerInfo(String id, String name) {
        seller = new SellerInfo();
        seller.id = id;
        seller.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductResponse) {
            ProductResponse productResponse = ((ProductResponse) o);
            return isEqualsTo(productResponse);
        } else return false;
    }

    private boolean isEqualsTo(ProductResponse response) {
        return title.equals(response.title)
                && description.equals(response.description)
                && suggestedPrice.equals(response.suggestedPrice)
                && category.equals(response.category)
                && id.equals(response.id)
                && createdAt.equals(response.createdAt);
    }
}
