package infra.penguin_client;

import infra.UriProvider;
import infra.dto.works.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PenguinWorksClient {
    private static final Logger log = LoggerFactory.getLogger(PenguinWorksClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriProvider uriProvider;

    public Work getPenguinBook(String workId) {
        Work work = restTemplate.getForObject(String.format(uriProvider.getPenguinBooksResourcesEndpoint() + "/works/%s", workId), Work.class);
        log.info(work.toString());
        return work;
    }
}