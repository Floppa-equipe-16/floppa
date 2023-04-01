package ulaval.glo2003.e2e;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.ResourceConfigProvider;

public abstract class ApiTestUtils extends JerseyTest {
    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected Application configure() {
        return new ResourceConfigProvider().provide(true);
    }
}
