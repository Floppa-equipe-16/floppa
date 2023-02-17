package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.domain.Offer;
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
        productRequest.validateProductNonNullParameter();

        Seller foundSeller = sellers.stream()
                .filter(seller -> xSellerId.equals(seller.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Seller with id '%s' not found", xSellerId)));

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

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") String productId) {
        Seller foundSeller = sellers.stream()
                .filter(seller -> seller.getProductById(productId) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", productId)));
        Product foundProduct = foundSeller.getProductById(productId);

        return Response.ok()
                .entity(new ProductResponse(foundSeller, foundProduct))
                .build();
    }

    @POST
    @Path("/{productId}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(
            @HeaderParam("X-Buyer-Username") String xBuyerUsername,
            @PathParam("productId") String productId,
            OfferRequest offerRequest) {
        offerRequest.validateOfferNonNullParameter();

        Seller foundSeller = sellers.stream()
                .filter(seller -> seller.getProductById(productId) != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id '%s' not found", productId)));

        Offer offer = new Offer(xBuyerUsername, offerRequest.amount, offerRequest.message);
        foundSeller.getProductById(productId).addOffer(offer);

        return Response.status(Response.Status.CREATED).build();
    }
}
