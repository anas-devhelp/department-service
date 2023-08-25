package com.example.departmentservice.config;

import com.example.departmentservice.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebclientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient employeeWebClient(){
        return WebClient
                .builder()
                .filter(filterFunction)
                .baseUrl("http://STUDENT-SERVICE")
                .build();
    }

    @Bean
    public EmployeeClient employeeClient(){
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(employeeWebClient())).build();
        return proxyFactory.createClient(EmployeeClient.class);
    }
}
