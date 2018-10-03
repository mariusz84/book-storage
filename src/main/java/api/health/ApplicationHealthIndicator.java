package api.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import services.health.HealthCheckService;

import java.util.Map;

@Component
public class ApplicationHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private HealthCheckService healthCheckService;

    @Override
    public void doHealthCheck(Health.Builder healthBuilder) {
        Map<String, Status> subsystemsHealth = healthCheckService.health();
        subsystemsHealth.forEach((name, status) -> {
            if (status.equals(Status.UP))
                healthBuilder.up().withDetail(name, status).build();
            else
                healthBuilder.down().withDetail(name, status).build();
        });
    }
}