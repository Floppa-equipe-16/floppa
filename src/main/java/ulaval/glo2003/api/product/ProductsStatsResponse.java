package ulaval.glo2003.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsStatsResponse {

    public Integer count;
    public Double avgAmount;

    public ProductsStatsResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof ProductsStatsResponse) {
            ProductsStatsResponse response = ((ProductsStatsResponse) o);
            return isEqualsTo(response);
        } else return false;
    }

    private boolean isEqualsTo(ProductsStatsResponse response) {
        return Objects.equals(count, response.count) && Objects.equals(avgAmount, response.avgAmount);
    }
}
