package api.http;

import api.error.exceptions.ForbiddenResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
import services.ip.IpService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DisallowGermanUsersFilter extends GenericFilterBean {
    private static final Logger log = LoggerFactory.getLogger(DisallowGermanUsersFilter.class);
    private static final String GERMANY = "Germany";

    @Autowired
    IpService ipService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            if (ipService.getUserCountryBasedOnIp(getIpAddress()).equals(GERMANY)) {
                throw new ForbiddenResourceException("German users are not allowed");
            }
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            log.info(e.getMessage());
        }
    }

    private String getIpAddress() {
        String ipAddress = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.info(e.getMessage());
        }
        return ipAddress;
    }
}