package services.ip;

import infra.ip_client.IpGeolocationClient;
import org.springframework.stereotype.Service;

@Service
public class IpService {
    private final IpGeolocationClient ipGeolocationClient;

    public IpService(final IpGeolocationClient ipGeolocationClient) {
        this.ipGeolocationClient = ipGeolocationClient;
    }

    public String getUserCountryBasedOnIp(final String ip) {
        return ipGeolocationClient.getCountryFromIp(ip);
    }
}