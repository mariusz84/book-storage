package api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocationConfig {

    @Value("location.result-page")
    private String location;

    public String getLocation() {
        return location;
    }
}
