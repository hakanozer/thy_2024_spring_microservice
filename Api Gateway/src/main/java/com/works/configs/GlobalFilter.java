package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class GlobalFilter implements Filter {

    final InfoRepository infoRepo;
    final Tracer tracer;
    private final InfoRepository infoRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String spanId = tracer.currentSpan().context().spanId();
        String traceId = tracer.currentSpan().context().traceId();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        String roles = "";
        if (auth != null) {
            username = auth.getName();
            roles = auth.getAuthorities().toString();
        }
        String sessionID = request.getSession().getId();
        String userAgent = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String time = System.currentTimeMillis() + "";

        response.setHeader("spanId", spanId);
        response.setHeader("traceId", traceId);

        Info i = new Info(null, spanId, traceId, username, roles, userAgent, ip, url, time, sessionID);
        infoRepository.save(i);

        filterChain.doFilter(request, response);
    }

}
