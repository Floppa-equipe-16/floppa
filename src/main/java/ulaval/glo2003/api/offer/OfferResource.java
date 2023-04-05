package ulaval.glo2003.api.offer;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.service.SellingService;

@Path("/products/{productId}/offers")
public class OfferResource {
    private final SellingService sellingService;

    public OfferResource(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(
            @HeaderParam("X-Buyer-Username") String xBuyerUsername,
            @PathParam("productId") String productId,
            OfferRequest offerRequest) {

        sellingService.createOffer(xBuyerUsername, productId, offerRequest);
        return Response.status(Response.Status.CREATED).build();
    }
}
