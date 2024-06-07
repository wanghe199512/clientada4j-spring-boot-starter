package com.clientAda4j.component;

import com.clientAda4j.ClientHeaderAdapter;
import com.clientAda4j.IClientAdaResponseFactory;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import com.clientAda4j.domain.ClientAdaCoreProp;

/**
 * @author wanghe
 */
public final class ExecutorBuilder {

    private ClientAdaCoreProp clientAdaCoreProp;

    private Class<? extends ClientHeaderAdapter> clientHeaderAdapter;

    private Class<? extends IClientAdaResponseFactory<?>> responseFactory;

    private DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda;

    public ExecutorBuilder(ClientAdaCoreProp clientAdaCoreProp, Class<? extends ClientHeaderAdapter> clientHeaderAdapter, Class<? extends IClientAdaResponseFactory<?>> responseFactory, DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda) {
        this.clientAdaCoreProp = clientAdaCoreProp;
        this.clientHeaderAdapter = clientHeaderAdapter;
        this.responseFactory = responseFactory;
        this.defaultClientInterfaceControllerAda = defaultClientInterfaceControllerAda;
    }

    public ClientAdaCoreProp getClientAdaCoreProp() {
        return clientAdaCoreProp;
    }

    public void setClientAdaCoreProp(ClientAdaCoreProp clientAdaCoreProp) {
        this.clientAdaCoreProp = clientAdaCoreProp;
    }

    public Class<? extends ClientHeaderAdapter> getClientHeaderAdapter() {
        return clientHeaderAdapter;
    }

    public void setClientHeaderAdapter(Class<? extends ClientHeaderAdapter> clientHeaderAdapter) {
        this.clientHeaderAdapter = clientHeaderAdapter;
    }

    public Class<? extends IClientAdaResponseFactory<?>> getResponseFactory() {
        return responseFactory;
    }

    public void setResponseFactory(Class<? extends IClientAdaResponseFactory<?>> responseFactory) {
        this.responseFactory = responseFactory;
    }

    public DefaultClientInterfaceControllerAda getDefaultClientInterfaceControllerAda() {
        return defaultClientInterfaceControllerAda;
    }

    public void setDefaultClientInterfaceControllerAda(DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda) {
        this.defaultClientInterfaceControllerAda = defaultClientInterfaceControllerAda;
    }
}
