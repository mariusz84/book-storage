package services.health;

import org.springframework.boot.actuate.health.Status;

public interface SystemHealth {
    String getSystemName();
    Status getSystemStatus();
}