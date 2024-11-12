package com.works.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppBeans {

    @Bean(name = "restTemplateNotConfig")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "restTemplateConfig")
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }

    @Bean
    public String url(RestTemplate restTemplateNotConfig) {
        return restTemplateNotConfig.toString();
    }

}
