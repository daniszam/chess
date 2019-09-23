package ru.kpfu.itis.darzam.chess.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static ru.kpfu.itis.darzam.chess.response_value.InvalidResponseValue.UNREACHABLE_POSITION;

@WebFilter("/horse/servlet/count")
public class HorseServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Map parametrMap = request.getParameterMap();
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        boolean validData = parametrMap.containsKey("startPosition") && parametrMap.containsKey("endPosition")
                && parametrMap.containsKey("width") && parametrMap.containsKey("height");

        try {
            Short.valueOf(width);
            Short.valueOf(height);
        } catch (NumberFormatException e) {
            validData = false;
        }

        if (!validData) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().println(UNREACHABLE_POSITION);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
