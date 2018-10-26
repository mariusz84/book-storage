package infra.ip_client;

import infra.UriProvider;
import infra.dto.ip.CountryIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IpGeolocationClient {
    private static final Logger log = LoggerFactory.getLogger(IpGeolocationClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UriProvider uriProvider;

    public String getCountryFromIp(final String ip) {
        CountryIp countryIp = restTemplate.getForObject(String.format(uriProvider.getIpGeolocationEndpoint() + "/ipgeo?apiKey=API_KEY&ip=%s", ip), CountryIp.class);
        log.info(countryIp.toString());
        return countryIp.getCountryName();
    }
}