package api.http;

import api.error.exceptions.ForbiddenResourceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.ip.IpService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.InetAddress;

import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.Silent.class)
//public class DisallowGermanUsersFilterTest {
//    private static final String IP = "10.10.251.107";
//    private static final String FRANCE = "France";

//    @Mock
//    private HttpServletRequest httpServletRequest;
//
//    @Mock
//    private HttpServletResponse httpServletResponse;
//
//    @Mock
//    private FilterChain filterChain;
//
//    @Mock
//    private IpService ipService;
//
//    @Mock
//    private InetAddress inetAddress;
//
//    @InjectMocks
//    private DisallowGermanUsersFilter disallowGermanUsersFilter;

//    @Test(expected = ForbiddenResourceException.class)
//    public void testDoFilterForGermanCustomer() {
//        DisallowGermanUsersFilter disallowGermanUsersFilterForPartialMock = new DisallowGermanUsersFilter();
//        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilterForPartialMock);
//
//        doReturn(IP).when(disallowGermanUsersFilterSpy).getIpAddress();
//        when(ipService.getUserCountryBasedOnIp(IP)).thenReturn("Germany");
//        disallowGermanUsersFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//    }
//
//    @Test
//    public void testDoFilterForNonGermanCustomer() {
//        DisallowGermanUsersFilter disallowGermanUsersFilterForPartialMock = new DisallowGermanUsersFilter();
//        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilterForPartialMock);
//
//        doReturn(IP).when(disallowGermanUsersFilterSpy).getIpAddress();
//        when(ipService.getUserCountryBasedOnIp(IP)).thenReturn(FRANCE);
//
//        disallowGermanUsersFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//    }
//
//    @Test(expected = IOException.class)
//    public void shouldThrowIOException() throws ServletException {
//        DisallowGermanUsersFilter disallowGermanUsersFilterForPartialMock = new DisallowGermanUsersFilter();
//        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilterForPartialMock);
//
//        doReturn(IP).when(disallowGermanUsersFilterSpy).getIpAddress();
//        when(ipService.getUserCountryBasedOnIp(IP)).thenReturn(FRANCE);
//
//        try {
//            doThrow(IOException.class).when(filterChain).doFilter(httpServletRequest, httpServletResponse);
//        } catch (IOException e) {
//        }
//
//        disallowGermanUsersFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//    }
//
//    @Test(expected = ServletException.class)
//    public void shouldThrowServletException() throws IOException {
//        DisallowGermanUsersFilter disallowGermanUsersFilterForPartialMock = new DisallowGermanUsersFilter();
//        DisallowGermanUsersFilter disallowGermanUsersFilterSpy = spy(disallowGermanUsersFilterForPartialMock);
//
//        doReturn(IP).when(disallowGermanUsersFilterSpy).getIpAddress();
//        when(ipService.getUserCountryBasedOnIp(IP)).thenReturn(FRANCE);
//
//        try {
//            doThrow(ServletException.class).when(filterChain).doFilter(httpServletRequest, httpServletResponse);
//        } catch (ServletException e) {
//        }
//
//        disallowGermanUsersFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//    }
//}