package ulaval.glo2003.api.offer;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.service.RepositoryManager;

@Path("/products/{productId}/offers")
public class OfferResource {
    private final RepositoryManager repositoryManager;

    public OfferResource(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffer(
            @HeaderParam("X-Buyer-Username") String xBuyerUsername,
            @PathParam("productId") String productId,
            OfferRequest offerRequest) {

        repositoryManager.createOffer(xBuyerUsername, productId, offerRequest);
        return Response.status(Response.Status.CREATED).build();
    }
}
