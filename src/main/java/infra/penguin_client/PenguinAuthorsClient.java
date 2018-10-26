package infra.penguin_client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import infra.UriProvider;
import infra.dto.authors.Authors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PenguinAuthorsClient {
    private static final Logger log = LoggerFactory.getLogger(PenguinAuthorsClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriProvider uriProvider;

    public Authors getPenguinAuthor(final String firstName, final String lastName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Authors authors = restTemplate.getForObject(String.format(uriProvider.getPenguinBooksResourcesEndpoint() + "/authors?firstName=%s&lastName=%s", firstName, lastName), Authors.class);
        log.info(authors.toString());
        return authors;
    }
}