package services.health;

import infra.health.MongoDbHealthCheck;
import infra.health.PenguinHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HealthCheckService {
    private static final String MONGO_DB = "MongoDb";
    private static final String PENGUIN = "Penguin";

    @Autowired
    private MongoDbHealthCheck mongoDbHealthCheck;

    @Autowired
    private PenguinHealthCheck penguinHealthCheck;

    public Map<String, Status> health() {
        return getSystemsHealth();
    }

    private Map<String, Status> getSystemsHealth() {
        List<SystemHealth> subsystems = Stream.of(getPenguinHealthCheckService(), getMongoDbHealthCheckService()).collect(Collectors.toList());
        return subsystems.stream().collect(Collectors.toMap(SystemHealth::getSystemName, SystemHealth::getSystemStatus));
    }

    @Bean
    private MongoDbHealthCheckService getMongoDbHealthCheckService() {
        return new MongoDbHealthCheckService(MONGO_DB, mongoDbHealthCheck);
    }

    @Bean
    private PenguinHealthCheckService getPenguinHealthCheckService() {
        return new PenguinHealthCheckService(PENGUIN, penguinHealthCheck);
    }
}