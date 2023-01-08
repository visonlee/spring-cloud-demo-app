package com.lws.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-service")
public interface ProviderClient {


    @GetMapping(path = "/greet")
    String greet();

}
