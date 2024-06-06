package com.clientAda4j;

import com.clientAda4j.component.ClientAdaProperties;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ClientAdaProperties.class})
public class ClientAdaAutoConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ClientAdaProperties clientAdaProperties;

    @Bean
    public DefaultClientInterfaceControllerAda init() {
        DefaultClientInterfaceControllerAda controllerAda = new DefaultClientInterfaceControllerAda(clientAdaProperties.getConnectTimeOut(), clientAdaProperties.getSocketTimeOut())
                .addClientHeadersAdapter(new ClientHeaderAdapter());
        logger.info("clientAda[{}] initial success", clientAdaProperties);
        return controllerAda;
    }
}
