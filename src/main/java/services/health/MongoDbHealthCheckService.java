package services.health;

import infra.health.MongoDbHealthCheck;
import org.springframework.boot.actuate.health.Status;

public class MongoDbHealthCheckService implements SystemHealth {
    private final MongoDbHealthCheck mongoDbHealthCheck;
    private final String systemName;

    MongoDbHealthCheckService(String systemName, MongoDbHealthCheck mongoDbHealthCheck) {
        this.mongoDbHealthCheck = mongoDbHealthCheck;
        this.systemName = systemName;
    }

    @Override
    public Status getSystemStatus() {
        return mongoDbHealthCheck.checkHealth();
    }

    @Override
    public String getSystemName() {
        return systemName;
    }
}