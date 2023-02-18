package ulaval.glo2003.api.seller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.service.RepositoryManager;

@Path("/sellers")
public class SellerResource {
    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        SellerResponse sellerResponse = RepositoryManager.getInstance().getSeller(sellerId);
        return Response.ok().entity(sellerResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, SellerRequest sellerRequest) {
        String sellerId = RepositoryManager.getInstance().createSeller(sellerRequest);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + sellerId)
                .build();
    }
}
