package ulaval.glo2003.api.seller;

import java.util.List;
import java.util.Objects;

public class SellerRankedCollectionResponse {

    public List<SellerResponse> sellers;

    public SellerRankedCollectionResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof SellerRankedCollectionResponse) {
            SellerRankedCollectionResponse response = ((SellerRankedCollectionResponse) o);
            return isEqualsTo(response);
        } else return false;
    }

    private boolean isEqualsTo(SellerRankedCollectionResponse response) {

        return Objects.equals(sellers, response.sellers);
    }
}
