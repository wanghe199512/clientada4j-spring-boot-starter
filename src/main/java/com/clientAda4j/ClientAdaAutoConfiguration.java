package com.clientAda4j;

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
        logger.info("clientAda4J SDK 初始化...");
        System.out.println("   ___ _ _         _     _      _      _ _  _ \n" +
                "  / __| (_)___ _ _| |_  /_\\  __| |__ _| | |(_)\n" +
                " | (__| | / -_) ' \\  _|/ _ \\/ _` / _` |_  _| |\n" +
                "  \\___|_|_\\___|_||_\\__/_/ \\_\\__,_\\__,_| |_|/ |\n" +
                "                                         |__/   Ver.1.0.0");
        return controllerAda;
    }
}
