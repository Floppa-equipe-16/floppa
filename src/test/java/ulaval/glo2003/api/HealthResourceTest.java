package ulaval.glo2003.api;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.e2e.ApiTestUtils;

import static com.google.common.truth.Truth.assertThat;

public class HealthResourceTest  extends ApiTestUtils {

    @Test
    public void areAllServicesUp(){
        Response response = target("/health").request().get();
        HealthResponse healthResponse = response.readEntity(HealthResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(MediaType.APPLICATION_JSON);

        assertThat(healthResponse.api).isTrue();
        assertThat(healthResponse.db).isTrue();
    }
}
