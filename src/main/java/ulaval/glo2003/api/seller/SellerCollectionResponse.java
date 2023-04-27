package ulaval.glo2003.api.seller;

import java.util.List;
import java.util.Objects;

public class SellerCollectionResponse {

    public List<SellerResponse> sellers;

    public SellerCollectionResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof SellerCollectionResponse) {
            SellerCollectionResponse response = ((SellerCollectionResponse) o);
            return isEqualsTo(response);
        } else return false;
    }

    private boolean isEqualsTo(SellerCollectionResponse response) {

        return Objects.equals(sellers, response.sellers);
    }
}
