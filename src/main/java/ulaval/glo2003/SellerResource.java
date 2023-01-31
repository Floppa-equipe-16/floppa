package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/sellers")
public class SellerResource {
    private final ArrayList<Seller> sellers = new ArrayList<>();

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        return Response.ok().entity(getSellerById(sellerId)).build();
    }

    private Seller getSellerById(String sellerId) {
        for (Seller seller : sellers) {
            if (seller.id.equals(sellerId)) {
                return seller;
            }
        }

        throw new NotFoundException(String.format("Seller with id '%s' not found", sellerId));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(Seller seller) {
        sellers.add(seller);
        return Response.status(Response.Status.CREATED).entity(seller.id).build();
    }
}
