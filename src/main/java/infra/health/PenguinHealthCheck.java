package infra.health;

import infra.UriProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PenguinHealthCheck {
    private static final Logger log = LoggerFactory.getLogger(PenguinHealthCheck.class);
    private HttpStatus httpStatus;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriProvider uriProvider;

    public PenguinHealthCheck() {
    }

    public Status checkHealth() {
        try {
            httpStatus = restTemplate.getForEntity(uriProvider.getPenguinBooksPingEndpoint(), String.class).getStatusCode();
        } catch (RestClientException restClientException) {
            log.info(restClientException.getMessage());
        }
        return httpStatus.value() == 200 ? Status.UP : Status.DOWN;
    }
}