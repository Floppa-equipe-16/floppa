package ulaval.glo2003.api.seller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.service.SellingService;

@Path("/sellers")
public class SellerResource {
    private final SellingService sellingService;

    public SellerResource(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        SellerResponse sellerResponse = sellingService.getSeller(sellerId);
        return Response.ok().entity(sellerResponse).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, SellerRequest sellerRequest) {
        String sellerId = sellingService.createSeller(sellerRequest);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + sellerId)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRankedSellers(@QueryParam("top") Integer top) {
        SellerCollectionResponse response = sellingService.getRankedSellers(top);
        return Response.ok().entity(response).build();
    }
}
