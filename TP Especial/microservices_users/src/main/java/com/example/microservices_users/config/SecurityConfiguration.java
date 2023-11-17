package com.example.microservices_users.config;

import com.example.microservices_users.security.jwt.JWTFilter;
import com.example.microservices_users.security.jwt.JwtConfigurer;
import com.example.microservices_users.security.jwt.TokenProvider;
import com.example.microservices_users.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtFilter;


    /**
     * Password encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)// descativo el crsf
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers( HttpMethod.PUT, "api/accounts/{id}/status").permitAll()
                            .requestMatchers( "/swagger-ui/**").permitAll()
                            .requestMatchers( "/v3/api-docs/**").permitAll()
                            .requestMatchers( HttpMethod.POST, "api/auth/authenticate", "api/auth/register").permitAll()

                            .requestMatchers( HttpMethod.POST, "api/users").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET, "api/users").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.DELETE,"api/users/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.PUT,"api/users/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET, "api/users/{id}").hasRole(Constants.ADMIN)

                            .requestMatchers( HttpMethod.GET, "api/users/alrededores/**").hasRole(Constants.USER)

                            .requestMatchers( HttpMethod.POST, "api/accounts").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET, "api/accounts").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.DELETE,"api/accounts/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.PUT,"api/accounts/{id}").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET, "api/accounts/{id}").hasRole(Constants.ADMIN)

                            .requestMatchers( HttpMethod.GET, "api/auth/admin").hasRole(Constants.ADMIN)
                            .requestMatchers( HttpMethod.GET, "api/auth/usuario").hasRole(Constants.USER)
                            .requestMatchers( HttpMethod.GET, "api/auth/mantenimiento").hasRole(Constants.MAINTENANCE)
                            .anyRequest()
                            .authenticated();
                } )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Nuestra configuracion de JWT.
     */
    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /**
     * Para la carga de datos
     */

    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_auth.sql"));
        return populator;
    }

}
