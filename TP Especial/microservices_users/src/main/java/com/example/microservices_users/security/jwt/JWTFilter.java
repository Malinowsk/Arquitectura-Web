package com.example.microservices_users.security.jwt;

import com.example.microservices_users.security.jwt.exception.ExpiredTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Filtro para validar el token.
 */

@Component
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = this.getToken( httpServletRequest );
        try {
            if ( StringUtils.hasText( jwt ) && this.tokenProvider.validateToken( jwt ) ) {
                Authentication authentication = this.tokenProvider.getAuthentication( jwt );
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        } catch ( ExpiredTokenException e ) { // Manejo el token expirado
            final var response = ((HttpServletResponse) servletResponse );
            response.setStatus( 498 );
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write( new JwtErrorDTO().toJson() );
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader( AUTHORIZATION_HEADER );
        if ( StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ") ) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Getter
    private static class JwtErrorDTO {
        private final JwtEnum code = JwtEnum.invalid_token;
        private final String message = "Token expired";
        private final String date = LocalDateTime.now().toString();

        public JwtErrorDTO(){}

        public String toJson() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (RuntimeException | JsonProcessingException ex ) {
                return String.format("{ message: %s }", this.message );
            }
        }
    }

    private enum JwtEnum { invalid_token }
}
