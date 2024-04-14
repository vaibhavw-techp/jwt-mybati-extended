package com.demo.jwt.JwtMybatisApplication.config.jwt;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimitFilter implements Filter {

    @Autowired
    private RateLimitingService rateLimitingService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Extract plan from JWT token or other source
        String plan = "FREE"; // Example: Replace this with actual plan extraction logic

        if (rateLimitingService.tryConsume(plan)) {
            chain.doFilter(request, response); // Request allowed, proceed
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            httpResponse.getWriter().write("API request limit linked to your current plan has been exhausted.");
        }
    }
}

