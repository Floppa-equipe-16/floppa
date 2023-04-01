package ulaval.glo2003.api;

import com.mongodb.client.MongoClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

    private final MongoClient client;

    public HealthResource(MongoClient client) {
        this.client = client;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {
        HealthResponse healthResponse = new HealthResponse();
        healthResponse.api = true;
        healthResponse.db = databaseHealthCheck(client);

        if (isHealthy(healthResponse)) {
            return Response.ok().entity(healthResponse).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.serverError().entity(healthResponse).build();
        }
    }

    private boolean isHealthy(HealthResponse healthResponse) {
        return healthResponse.api && healthResponse.db;
    }

    private boolean databaseHealthCheck(MongoClient client) {
        try {
            client.listDatabaseNames().first();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
