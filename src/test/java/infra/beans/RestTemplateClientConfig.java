package infra.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
public class RestTemplateClientConfig {

    @Bean
    public RestTemplateClient getRestTemplateClient() {
        return new RestTemplateClient();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return mock(RestTemplate.class);
    }
}