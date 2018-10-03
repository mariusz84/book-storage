package infra;

import org.springframework.beans.factory.annotation.Value;

public class UriProvider {

    @Value("${penguin-books.endpoint.ping}")
    private String penguinBooksPingEndpoint;

    @Value("${penguin-books.endpoint.resources}")
    private String penguinBooksResourcesEndpoint;

    @Value("${ip-geolocation.endpoint}")
    private String ipGeolocationEndpoint;

    public String getPenguinBooksPingEndpoint() {
        return penguinBooksPingEndpoint;
    }

    public String getPenguinBooksResourcesEndpoint() {
        return penguinBooksResourcesEndpoint;
    }

    public String getIpGeolocationEndpoint() {
        return ipGeolocationEndpoint;
    }
}