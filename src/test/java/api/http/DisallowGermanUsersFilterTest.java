package api.http;

import api.error.exceptions.ForbiddenResourceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.ip.IpService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DisallowGermanUsersFilterTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterChain filterChain;

    @Mock
    private IpService ipService;

    @InjectMocks
    private DisallowGermanUsersFilter disallowGermanUsersFilter;

    @Test
    public void testDoFilterForGermanCustomer() {
        DisallowGermanUsersFilter disallowGermanUsersFilter = new DisallowGermanUsersFilter();
        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilter);

        when(ipService.getUserCountryBasedOnIp("1.0.0.0")).thenReturn("Germany");

        doThrow(ForbiddenResourceException.class).when(disallowGermanUsersFilterSpy).doFilter(httpServletRequest, httpServletResponse, filterChain);
    }

    @Test
    public void testDoFilterForNonGermanCustomer() {
        DisallowGermanUsersFilter disallowGermanUsersFilter = new DisallowGermanUsersFilter();
        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilter);

        when(ipService.getUserCountryBasedOnIp("2.0.0.0")).thenReturn("France");

        doNothing().when(disallowGermanUsersFilterSpy).doFilter(httpServletRequest, httpServletResponse, filterChain);
    }
}