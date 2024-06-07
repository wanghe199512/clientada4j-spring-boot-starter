package com.clientAda4j;

import com.clientAda4j.component.ClientAdaProperties;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.clientAda4j"})
@EnableConfigurationProperties({ClientAdaProperties.class})
@ConditionalOnProperty(prefix = "clientada4j", name = "enabled", havingValue = "true")
public class ClientAdaAutoConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ClientAdaProperties clientAdaProperties;

    public ClientAdaAutoConfiguration(ClientAdaProperties clientAdaProperties) {
        this.clientAdaProperties = clientAdaProperties;
    }

    @Bean
    public DefaultClientInterfaceControllerAda init() {
        DefaultClientInterfaceControllerAda controllerAda = new DefaultClientInterfaceControllerAda(
                clientAdaProperties.getConnectTimeOut(), clientAdaProperties.getSocketTimeOut(), clientAdaProperties.getPoolingConnectionMaxTotal(), clientAdaProperties.getDefaultMaxPerRouteTotal());
        logger.info("clientAda SDK 初始化...\n {}", "");
        return controllerAda;
    }
}
