package ulaval.glo2003.api.offer;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.OfferConverter;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellersDatabase;

@Path("/products/{productId}/offers")
public class OfferResource {
    private final SellersDatabase sellersDatabase;

    public OfferResource(SellersDatabase sellersDatabase) {
        this.sellersDatabase = sellersDatabase;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(
            @HeaderParam("X-Buyer-Username") String xBuyerUsername,
            @PathParam("productId") String productId,
            OfferRequest offerRequest) {
        offerRequest.validateOfferNonNullParameter();

        Seller foundSeller = sellersDatabase.findSellerByProductId(productId);
        Offer offer = OfferConverter.offerRequestToOffer(xBuyerUsername, offerRequest);
        foundSeller.getProductById(productId).addOffer(offer);

        return Response.status(Response.Status.CREATED).build();
    }
}
