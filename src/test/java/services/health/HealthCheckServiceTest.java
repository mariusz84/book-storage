package services.health;

import infra.health.MongoDbHealthCheck;
import infra.health.PenguinHealthCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.health.Status;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckServiceTest {
    private static final String MONGO_DB = "MongoDb";
    private static final String PENGUIN = "Penguin";

    @Mock
    private PenguinHealthCheck penguinHealthCheck;

    @Mock
    private MongoDbHealthCheck mongoDbHealthCheck;

    @InjectMocks HealthCheckService healthCheckService;

    @Test
    public void shouldReturnStatusUpWhenAllSubSystemsAreUp() {
        when(mongoDbHealthCheck.checkHealth()).thenReturn(Status.UP);
        when(penguinHealthCheck.checkHealth()).thenReturn(Status.UP);

        Map<String, Status> result = healthCheckService.health();

        assertThat(result.get(MONGO_DB), equalTo(Status.UP));
        assertThat(result.get(PENGUIN), equalTo(Status.UP));
    }

    @Test
    public void shouldReturnStatusDownWhenFirstSubSystemIsDown() {
        when(mongoDbHealthCheck.checkHealth()).thenReturn(Status.DOWN);
        when(penguinHealthCheck.checkHealth()).thenReturn(Status.UP);

        Map<String, Status> result = healthCheckService.health();

        assertThat(result.get(MONGO_DB), equalTo(Status.DOWN));
        assertThat(result.get(PENGUIN), equalTo(Status.UP));
    }

    @Test
    public void shouldReturnStatusDownWhenSecondSubSystemIsDown() {
        when(mongoDbHealthCheck.checkHealth()).thenReturn(Status.UP);
        when(penguinHealthCheck.checkHealth()).thenReturn(Status.DOWN);

        Map<String, Status> result = healthCheckService.health();

        assertThat(result.get(MONGO_DB), equalTo(Status.UP));
        assertThat(result.get(PENGUIN), equalTo(Status.DOWN));
    }
}