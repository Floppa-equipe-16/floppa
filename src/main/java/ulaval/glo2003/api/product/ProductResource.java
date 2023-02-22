package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.service.RepositoryManager;

@Path("/products")
public class ProductResource {
    private final RepositoryManager repositoryManager;

    public ProductResource(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(
            @Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId, ProductRequest productRequest) {
        String productId = repositoryManager.createProduct(xSellerId, productRequest);
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

        ProductCollectionResponse productResponses = repositoryManager.getProducts(productFilter);
        return Response.ok().entity(productResponses).build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") String productId) {
        ProductResponse productResponse = repositoryManager.getProduct(productId);
        return Response.ok().entity(productResponse).build();
    }
}
