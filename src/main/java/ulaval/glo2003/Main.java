package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.HealthResource;
import ulaval.glo2003.api.SellerResource;
import ulaval.glo2003.api.exceptionHandling.NotFoundExceptionMapper;
import ulaval.glo2003.api.exceptionHandling.ParamExceptionMapper;
import ulaval.glo2003.domain.Seller;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Seller> sellers = new ArrayList<>();

        HealthResource healthResource = new HealthResource();
        SellerResource sellerResource = new SellerResource(sellers);
        ParamExceptionMapper paramExceptionMapper = new ParamExceptionMapper();
        NotFoundExceptionMapper notFoundExceptionMapper = new NotFoundExceptionMapper();
        ResourceConfig resourceConfig =
                new ResourceConfig()
                        .register(healthResource)
                        .register(sellerResource)
                        .register(paramExceptionMapper)
                        .register(notFoundExceptionMapper);
        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
