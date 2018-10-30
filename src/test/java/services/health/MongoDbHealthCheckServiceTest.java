package services.health;

import infra.health.MongoDbHealthCheck;
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
public class MongoDbHealthCheckServiceTest {

    @Mock
    private MongoDbHealthCheck mongoDbHealthCheck;

    @InjectMocks
    private MongoDbHealthCheckService mongoDbHealthCheckService;

    @Test
    public void shouldReturnStatusUpIfMongoDbIsUp() {
        when(mongoDbHealthCheck.checkHealth()).thenReturn(Status.UP);

        assertThat(mongoDbHealthCheckService.getSystemStatus(), equalTo(Status.UP));
    }

    @Test
    public void shouldReturnStatusDownIfMongoDbIsDown() {
        when(mongoDbHealthCheck.checkHealth()).thenReturn(Status.DOWN);

        assertThat(mongoDbHealthCheckService.getSystemStatus(), equalTo(Status.DOWN));
    }

    @Test
    public void shouldReturnSystemName() {
        MongoDbHealthCheckService mongoDbService = new MongoDbHealthCheckService("MONGO_DB", mongoDbHealthCheck);

        assertThat(mongoDbService.getSystemName(), equalTo("MONGO_DB"));
    }
}