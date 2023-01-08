package com.lws.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("provider")
@RefreshScope
@Data
@Configuration
public class ProviderAppProperties {
    private String msg;
}
