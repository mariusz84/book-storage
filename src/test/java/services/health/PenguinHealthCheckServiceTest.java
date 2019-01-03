package services.health;

import infra.health.PenguinHealthCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.health.Status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PenguinHealthCheckServiceTest {

    @Mock
    private PenguinHealthCheck penguinHealthCheck;

    @InjectMocks
    private PenguinHealthCheckService penguinHealthCheckService;

    @Test
    public void shouldReturnStatusUpIfMongoDbIsUp() {
        when(penguinHealthCheck.checkHealth()).thenReturn(Status.UP);

        assertThat(penguinHealthCheckService.getSystemStatus(), equalTo(Status.UP));
    }

    @Test
    public void shouldReturnStatusDownIfMongoDbIsDown() {
        when(penguinHealthCheck.checkHealth()).thenReturn(Status.DOWN);

        assertThat(penguinHealthCheckService.getSystemStatus(), equalTo(Status.DOWN));
    }

    @Test
    public void shouldReturnSystemName() {
        PenguinHealthCheckService penguinService = new PenguinHealthCheckService("PENGUIN", penguinHealthCheck);

        assertThat(penguinService.getSystemName(), equalTo("PENGUIN"));
    }
}