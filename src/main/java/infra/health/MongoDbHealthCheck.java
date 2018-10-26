package infra.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.mongo.MongoHealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDbHealthCheck {

    @Autowired
    MongoTemplate mongoTemplate;

    public MongoDbHealthCheck() {
    }

    public Status checkHealth() {
        return (new MongoHealthIndicator(mongoTemplate).health().getStatus().equals(Status.UP)) ? Status.UP : Status.DOWN;
    }
}