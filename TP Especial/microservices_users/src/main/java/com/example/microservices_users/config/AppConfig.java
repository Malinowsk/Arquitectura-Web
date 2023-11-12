package com.example.microservices_users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class AppConfig {

    @Bean("restClient")
    public RestTemplate registerRestTemplate() { return new RestTemplate();}
}
