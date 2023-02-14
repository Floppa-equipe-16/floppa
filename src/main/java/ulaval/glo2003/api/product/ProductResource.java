package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

@Path("/products")
public class ProductResource {
    private final List<Seller> sellers;

    public ProductResource(List<Seller> sellers) {
        this.sellers = sellers;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(
            @Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId, ProductRequest productRequest) {
        Seller foundSeller = sellers.stream()
                .filter(seller -> xSellerId.equals(seller.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", xSellerId)));

        productRequest.validateProductNonNullParameter();
        Product product = new Product(
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                productRequest.category);
        foundSeller.addProduct(product);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + product.getId())
                .build();
    }
}
