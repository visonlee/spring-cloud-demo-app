package com.lws.consumer.controller;

import com.lws.consumer.client.ProviderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProviderClient providerFeignClient;

    @GetMapping("/rest")
    public String rest() {
        log.info("ConsumerController received request (resttemplate)");
        showServiceInstances();

        final ResponseEntity<String> response = restTemplate
                .exchange("http://provider-service/greet/", HttpMethod.GET, null, String.class);

        return response.getBody();
    }

    @GetMapping("/feign")
    public String feign() {
        log.info("ConsumerController received request (feign)");
        showServiceInstances();
        return providerFeignClient.greet();
    }

    private void showServiceInstances() {
        log.info("DiscoveryClient: {}", discoveryClient.getClass().getName());
        final List<ServiceInstance> instances = discoveryClient.getInstances("provider-service");
        instances.forEach(s -> {
            log.info("Host: {}, Port: {}", s.getHost(), s.getPort());
        });
    }
}
