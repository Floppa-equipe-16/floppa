package ulaval.glo2003;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/sellers")
public class SellerResource {

    private final ArrayList<Seller> sellers = new ArrayList<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(Seller seller) {
        sellers.add(seller);
        return Response.status(Response.Status.CREATED).build();
    }
}
