package dev.mrkresnofatih.africanpenguin.middlewares;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    protected void throwExceptionWithCode(ServletResponse servletResponse,
                                          String errorCode) throws IOException {
        var httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        var objectMapper = new ObjectMapper();
        var responsePackage = new ResponseHandler<String>().WrapFailure(errorCode);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().print(objectMapper.writeValueAsString(responsePackage));
        httpServletResponse.getWriter().flush();
    }
}
