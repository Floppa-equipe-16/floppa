package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.HealthResource;
import ulaval.glo2003.api.exceptionMappers.NotFoundExceptionMapper;
import ulaval.glo2003.api.exceptionMappers.ParamExceptionMapper;
import ulaval.glo2003.api.offer.OfferResource;
import ulaval.glo2003.api.product.ProductResource;
import ulaval.glo2003.api.seller.SellerResource;
import ulaval.glo2003.service.RepositoryManager;
import ulaval.glo2003.service.RepositoryManagerFactory;

public class Main {

    public static void main(String[] args) throws IOException {
        RepositoryManagerFactory factory = new RepositoryManagerFactory();
        RepositoryManager repositoryManager = factory.create();

        HealthResource healthResource = new HealthResource();
        SellerResource sellerResource = new SellerResource(repositoryManager);
        ProductResource productResource = new ProductResource(repositoryManager);
        OfferResource offerResource = new OfferResource(repositoryManager);

        ParamExceptionMapper paramExceptionMapper = new ParamExceptionMapper();
        NotFoundExceptionMapper notFoundExceptionMapper = new NotFoundExceptionMapper();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(healthResource)
                .register(sellerResource)
                .register(productResource)
                .register(offerResource)
                .register(paramExceptionMapper)
                .register(notFoundExceptionMapper);
        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
