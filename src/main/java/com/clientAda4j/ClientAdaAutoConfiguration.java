package com.clientAda4j;

import com.clientAda4j.properties.ClientAdaProperties;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientAdaAutoConfiguration {
    @Autowired
    private final ClientAdaProperties clientAdaProperties;

    public ClientAdaAutoConfiguration(ClientAdaProperties clientAdaProperties) {
        this.clientAdaProperties = clientAdaProperties;
    }

    @Bean
    public DefaultClientInterfaceControllerAda init() {
        return new DefaultClientInterfaceControllerAda(clientAdaProperties.getConnectTimeOut(), clientAdaProperties.getSocketTimeOut())
                .addClientHeadersAdapter(new ClientAdaHeaderAdapter());
    }
}
