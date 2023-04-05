package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.HealthResponse;

public class HealthResourceTest extends ApiTest {

    @Test
    public void areAllServicesUp() {
        Response response = target("/health").request().get();
        HealthResponse healthResponse = response.readEntity(HealthResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);

        assertThat(healthResponse.api).isTrue();
        assertThat(healthResponse.db).isTrue();
    }
}
