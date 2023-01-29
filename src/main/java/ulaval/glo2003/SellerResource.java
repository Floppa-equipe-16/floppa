package ulaval.glo2003;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/sellers")
public class SellerResource {

    @POST
    public Response createSeller() {
        return Response.status(Response.Status.CREATED).build();
    }
}
