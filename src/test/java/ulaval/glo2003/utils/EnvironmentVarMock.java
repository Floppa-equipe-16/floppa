package ulaval.glo2003.utils;

import org.mockito.MockedStatic;
import ulaval.glo2003.EnvironmentVariable;

public class EnvironmentVarMock {

    public static void mockEnvVarEmail(MockedStatic<EnvironmentVariable> mock, String returnEmail) {
        mock.when(EnvironmentVariable::getFloppaHostEmail).thenReturn(returnEmail);
    }

    public static void mockEnvVarPassword(MockedStatic<EnvironmentVariable> mock, String returnPassword) {
        mock.when(EnvironmentVariable::getFloppaHostPassword).thenReturn(returnPassword);
    }
}
