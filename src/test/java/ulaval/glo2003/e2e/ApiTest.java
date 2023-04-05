package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import dev.morphia.Datastore;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ulaval.glo2003.ResourceConfigProvider;
import ulaval.glo2003.domain.exceptions.ErrorDescription;
import ulaval.glo2003.domain.infrastructure.mongo.MongoOfferRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoProductRepository;
import ulaval.glo2003.domain.infrastructure.mongo.MongoSellerRepository;
import ulaval.glo2003.service.SellingService;
import ulaval.glo2003.service.SellingServiceFactory;
import ulaval.glo2003.utils.MongoTestUtils;

public abstract class ApiTest {

    private static final Datastore datastore = MongoTestUtils.createLocalDatastore();
    private static JerseyTest jerseyTest;
    protected static SellingService sellingService = new SellingServiceFactory().create(datastore);

    public ApiTest() {}

    @BeforeAll
    public static void setUpServer() throws Exception {
        initJerseyTest();
        jerseyTest.setUp();
    }

    @AfterAll
    public static void tearDownServer() throws Exception {
        jerseyTest.tearDown();
    }

    @AfterEach
    public void tearDown() {
        resetLocalDB();
    }

    private void resetLocalDB() {
        new MongoOfferRepository(datastore).reset();
        new MongoSellerRepository(datastore).reset();
        new MongoProductRepository(datastore).reset();
    }

    protected WebTarget target(String path) {
        return jerseyTest.target(path);
    }

    protected String getErrorCode(Response response) {
        return response.readEntity(ErrorDescription.class).code;
    }

    protected void assertMediaTypeIsJson(Response response) {
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(MediaType.APPLICATION_JSON);
    }

    private static void initJerseyTest() {
        jerseyTest = new JerseyTest() {
            @Override
            protected Application configure() {
                return new ResourceConfigProvider().provide(true);
            }
        };
    }
}
