package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    public static void main(String[] args) throws IOException {
        HealthResource healthResource = new HealthResource();
        SellerResource sellerResource = new SellerResource();
        NotFoundExceptionMapper notFoundExceptionMapper = new NotFoundExceptionMapper();
        ResourceConfig resourceConfig =
                new ResourceConfig()
                        .register(healthResource)
                        .register(sellerResource)
                        .register(notFoundExceptionMapper);

        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
