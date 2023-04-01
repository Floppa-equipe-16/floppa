package ulaval.glo2003.api;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.e2e.ApiTestUtils;

public class RootResourceTest extends ApiTestUtils {

    @Test
    public void work() {
        Response response = target("/").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
    }
}
