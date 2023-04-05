package ulaval.glo2003.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.offer.SelectedOfferResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    public static class SellerInfo {
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
    public SelectedOfferResponse selectedOffer;

    public ProductResponse() {}

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
        return Objects.equals(title, response.title)
                && Objects.equals(description, response.description)
                && Objects.equals(suggestedPrice, response.suggestedPrice)
                && Objects.equals(category, response.category)
                && Objects.equals(id, response.id)
                && Objects.equals(createdAt, response.createdAt)
                && Objects.equals(saleStatus, response.saleStatus)
                && isSellerEquals(response.seller)
                && Objects.equals(offers, response.offers);
    }

    private boolean isSellerEquals(SellerInfo response) {
        if (seller == null && response == null) return true;
        else if (seller == null) return false;
        else if (response == null) return false;
        else {
            return Objects.equals(seller.name, response.name) && Objects.equals(seller.id, response.id);
        }
    }
}
