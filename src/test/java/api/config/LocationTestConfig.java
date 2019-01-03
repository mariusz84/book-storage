package api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationTestConfig {

    @Bean
    public LocationConfig getLocationConfig() {
        return new LocationConfig();
    }
}