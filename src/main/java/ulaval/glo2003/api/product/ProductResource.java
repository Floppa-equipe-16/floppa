package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.Map;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.service.SellingService;

@Path("/products")
public class ProductResource {
    private final SellingService sellingService;

    public ProductResource(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProducts(
            @Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId, ProductRequest productRequest) {
        String productId = sellingService.createProduct(xSellerId, productRequest);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + productId)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(
            @QueryParam("sellerId") String sellerId,
            @QueryParam("title") String title,
            @QueryParam("category") String category,
            @QueryParam("minPrice") Double minPrice,
            @QueryParam("maxPrice") Double maxPrice) {
        ProductFilter productFilter = new ProductFilter(sellerId, title, category, minPrice, maxPrice);

        ProductCollectionResponse productResponses = sellingService.getProducts(productFilter);
        return Response.ok().entity(productResponses).build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") String productId) {
        ProductResponse productResponse = sellingService.getProduct(productId);
        return Response.ok().entity(productResponse).build();
    }

    @POST
    @Path("/{productId}/sell")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sellProduct(
            @HeaderParam("X-Seller-Id") String xSellerId,
            @PathParam("productId") String productId,
            Map<String, String> body) {
        var username = body.get("username");
        sellingService.sellProduct(xSellerId, productId, username);

        return Response.ok().build();
    }
}
