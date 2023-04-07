package ulaval.glo2003.api.product;

import ulaval.glo2003.domain.exceptions.MissingParamException;

public class ProductSellRequest {
    public String username;

    public void validate() {
        if (username == null) throw new MissingParamException("username");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductSellRequest) {
            ProductSellRequest productSellRequest = ((ProductSellRequest) o);
            return isEqualsTo(productSellRequest);
        } else return false;
    }

    private boolean isEqualsTo(ProductSellRequest request) {
        return username.equals(request.username);
    }
}
