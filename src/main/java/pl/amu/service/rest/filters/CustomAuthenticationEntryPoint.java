/*package pl.amu.service.rest.filters;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by win10 on 25.04.2017.
 */
/*
@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException ) throws IOException, ServletException {
    /*    response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{ \"message\": \"" + authException.getMessage() + "\", \"code\": \"BAD_CREDENTIALS\" }");*/
/*    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        setRealmName("BasicAuthRealm");
//        super.afterPropertiesSet();
    }
}
*/