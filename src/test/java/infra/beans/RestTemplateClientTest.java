package infra.beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTemplateClientConfig.class)
public class RestTemplateClientTest {

    @Autowired
    private RestTemplateClient restTemplateClient;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnRestTemplate() {
        assertThat(restTemplateClient.restTemplate(), instanceOf(RestTemplate.class));
    }
}