package com.in28minutes.microservices.limitsservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
// picks up the prefix 'limits-service' in properties file
@ConfigurationProperties("limits-service")
public class Configuration {
    private int minimum;
    private int maximum;
}
