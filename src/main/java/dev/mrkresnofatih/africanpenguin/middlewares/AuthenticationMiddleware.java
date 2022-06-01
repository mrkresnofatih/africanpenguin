package dev.mrkresnofatih.africanpenguin.middlewares;

import dev.mrkresnofatih.africanpenguin.constants.ApplicationErrorCodes;
import dev.mrkresnofatih.africanpenguin.constants.ImportantEndpoints;
import dev.mrkresnofatih.africanpenguin.services.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationMiddleware extends BaseFilter {
    private final IAuthService authService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationMiddleware.class);

    @Autowired
    public AuthenticationMiddleware(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        var endpointPath = ((HttpServletRequest) servletRequest).getRequestURI();
        if (ImportantEndpoints.NoAuthEndpoints.contains(endpointPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        var authenticationHeader = ((HttpServletRequest) servletRequest).getHeader("Auth");
        if (Objects.isNull(authenticationHeader)) {
            throwExceptionWithCode(servletResponse, ApplicationErrorCodes.INCORRECT_CREDENTIALS);
            return;
        }

        if (!authService.ValidateToken(authenticationHeader)) {
            throwExceptionWithCode(servletResponse, ApplicationErrorCodes.INCORRECT_CREDENTIALS);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
