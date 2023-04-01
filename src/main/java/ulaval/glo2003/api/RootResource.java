package ulaval.glo2003.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class RootResource {

    @GET
    @Path("/")
    public Response root() {
        return Response.status(Response.Status.OK).build();
    }
}
