package com.lws.provider.controller;

import com.lws.provider.config.ProviderAppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Slf4j
public class ProviderController {


    @Autowired
    Environment environment;

    @Autowired
    private ProviderAppProperties providerAppProperties;

    @GetMapping("/greet")
    public String hello() throws UnknownHostException {

        InetAddress address = InetAddress.getLocalHost();
        String port = this.environment.getProperty("local.server.port");

        log.info("ProviderController received request: " + address.getHostName() + ":" + port);

        return providerAppProperties.getMsg() + ", from " + address.getHostName() + ":" + port;
    }
}
