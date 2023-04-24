package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ulaval.glo2003.EnvironmentVariable;
import ulaval.glo2003.domain.notification.EmailAuthentication;
import ulaval.glo2003.domain.notification.EmailHost;
import ulaval.glo2003.service.NotificationService;
import ulaval.glo2003.service.NotificationServiceFactory;
import ulaval.glo2003.utils.EnvironmentVarMock;

public class NotificationServiceFactoryTest {

    private final NotificationServiceFactory factory = new NotificationServiceFactory();

    @Test
    public void canCreateNotificationService() {
        NotificationService service = factory.create(false);

        assertThat(service).isNotNull();
    }

    @Test
    public void canGetEmailHost() {
        EmailHost emailHost = factory.getEmailHost();

        assertThat(emailHost.port).isNotNull();
        assertThat(emailHost.smtpDomain).isNotNull();
    }

    @Test
    public void canGetEmailAuthenticationWhenEnvVarSet() {
        String email = "test";
        String password = "something";
        try (MockedStatic<EnvironmentVariable> environmentVariableMockedStatic =
                     Mockito.mockStatic(EnvironmentVariable.class, Mockito.CALLS_REAL_METHODS)) {

            EnvironmentVarMock.mockEnvVarEmail(environmentVariableMockedStatic,email);
            EnvironmentVarMock.mockEnvVarPassword(environmentVariableMockedStatic, password);

            EmailAuthentication emailAuthentication = factory.getEmailAuthentication();

            assertThat(emailAuthentication).isNotNull();
            assertThat(emailAuthentication.email).isEqualTo(email);
            assertThat(emailAuthentication.password).isEqualTo(password);
        }
    }

    @Test void canGetEmailAuthenticationWhenEnvVarMissing() {
        try (MockedStatic<EnvironmentVariable> environmentVariableMockedStatic =
                     Mockito.mockStatic(EnvironmentVariable.class, Mockito.CALLS_REAL_METHODS)) {

            EnvironmentVarMock.mockEnvVarEmail(environmentVariableMockedStatic,null);
            EnvironmentVarMock.mockEnvVarPassword(environmentVariableMockedStatic, null);

            EmailAuthentication emailAuthentication = factory.getEmailAuthentication();

            assertThat(emailAuthentication).isNull();
        }

    }

}
