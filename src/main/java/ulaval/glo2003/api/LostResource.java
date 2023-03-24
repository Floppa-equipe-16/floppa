package ulaval.glo2003.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/{else}")
public class LostResource {
    @GET
    public Response lost() {
        return Response.status(404).build();
    }
}
