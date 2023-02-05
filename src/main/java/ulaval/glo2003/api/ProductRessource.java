package ulaval.glo2003.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.api.ProductCategory;
import ulaval.glo2003.api.exceptionHandling.ProductItemNotFoundException;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductRessource {
    private final List<Seller> sellers;
    public ProductRessource(List<Seller> sellers) {
        this.sellers = sellers;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(@Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId,ProductRequest productRequest) {
        Seller foundSeller = sellers.stream()
                .filter(seller -> xSellerId.equals(seller.getId()))
                .findFirst()
                .orElseThrow(
                () ->
                        new ProductItemNotFoundException
                                ("Seller id: " + xSellerId + " not found"));

        productRequest.validateProductNonNullParameter();
        Product product = new Product(productRequest.title,
                    productRequest.description,
                    productRequest.suggestedPrice,
                    productRequest.category);
        foundSeller.addProducts(product);
        return Response.status(Response.Status.CREATED).header("Location", uriInfo.getAbsolutePath() + "/" + product.getId()).build();
    }
}
