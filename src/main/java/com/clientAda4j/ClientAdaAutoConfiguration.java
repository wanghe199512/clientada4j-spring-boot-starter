package com.clientAda4j;

import com.clientAda4j.properties.ClientAdaProperties;
import com.clientAda4j.rest.DefaultClientInterfaceRestControllerAda;
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
    public DefaultClientInterfaceRestControllerAda init() {
        return new DefaultClientInterfaceRestControllerAda(clientAdaProperties.getConnectTimeOut(), clientAdaProperties.getSocketTimeOut())
                .addClientHeadersAdapter(new ClientAdaHeaderAdapter());
    }
}
