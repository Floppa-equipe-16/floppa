package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.service.notification.SessionException;

public class Main {
    public static void main(String[] args) throws IOException, SessionException {

        ResourceConfig resourceConfig = new ResourceConfigProvider().provide(false);

        URI uri = URI.create("http://0.0.0.0:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
