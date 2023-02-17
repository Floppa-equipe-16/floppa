package ulaval.glo2003.api.seller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellersDatabase;

@Path("/sellers")
public class SellerResource {
    private final SellersDatabase sellersDatabase;

    public SellerResource(SellersDatabase sellersDatabase) {
        this.sellersDatabase = sellersDatabase;
    }

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller foundSeller = sellersDatabase.findSellerBySellerId(sellerId);
        return Response.ok().entity(new SellerResponse(foundSeller)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, SellerRequest sellerRequest) {
        sellerRequest.validateSellerNonNullParameters();
        Seller seller = new Seller(
                sellerRequest.name,
                sellerRequest.birthdate,
                sellerRequest.email,
                sellerRequest.phoneNumber,
                sellerRequest.bio);
        sellersDatabase.addSeller(seller);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + seller.getId())
                .build();
    }
}
