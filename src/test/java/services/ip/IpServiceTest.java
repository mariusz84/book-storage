package services.ip;

import infra.ip_client.IpGeolocationClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IpServiceTest {
    private static final String IP = "1.0.0.0";

    @Mock
    private IpGeolocationClient ipGeolocationClient;

    @InjectMocks
    private IpService ipService;

    @Test
    public void shouldReturnUserCountryBasedOnIp() {
        when(ipGeolocationClient.getCountryFromIp(IP)).thenReturn("Germany");

        assertThat(ipService.getUserCountryBasedOnIp(IP), equalTo("Germany"));
    }
}