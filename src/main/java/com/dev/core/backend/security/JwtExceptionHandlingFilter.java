package com.dev.core.backend.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class JwtExceptionHandlingFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if (failed.getCause() instanceof ExpiredJwtException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired");
        } else {
            super.unsuccessfulAuthentication(request, response, failed);
        }
    }
}