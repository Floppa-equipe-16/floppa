package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellersDatabase;

@Path("/products")
public class ProductResource {
    private final SellersDatabase sellersDatabase;

    public ProductResource(SellersDatabase sellersDatabase) {
        this.sellersDatabase = sellersDatabase;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(
            @Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId, ProductRequest productRequest) {
        productRequest.validateProductNonNullParameter();

        Seller foundSeller = sellersDatabase.findSellerBySellerId(xSellerId);

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
        Seller foundSeller = sellersDatabase.findSellerByProductId(productId);
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

        Seller foundSeller = sellersDatabase.findSellerByProductId(productId);

        Offer offer = new Offer(xBuyerUsername, offerRequest.amount, offerRequest.message);
        foundSeller.getProductById(productId).addOffer(offer);

        return Response.status(Response.Status.CREATED).build();
    }
}
