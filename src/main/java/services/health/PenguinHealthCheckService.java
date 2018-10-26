package services.health;

import infra.health.PenguinHealthCheck;
import org.springframework.boot.actuate.health.Status;

public class PenguinHealthCheckService implements SystemHealth {
    private final PenguinHealthCheck penguinHealthCheck;
    private final String systemName;

    PenguinHealthCheckService(String systemName, PenguinHealthCheck penguinHealthCheck) {
        this.penguinHealthCheck = penguinHealthCheck;
        this.systemName = systemName;
    }

    @Override
    public Status getSystemStatus() {
        return penguinHealthCheck.checkHealth();
    }

    @Override
    public String getSystemName() {
        return systemName;
    }
}