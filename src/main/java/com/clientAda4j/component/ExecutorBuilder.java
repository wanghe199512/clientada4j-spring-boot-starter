package com.clientAda4j.component;

import com.clientAda4j.ClientHeaderAdapter;
import com.clientAda4j.DefaultClientAdaResponseFactory;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import com.clientAda4j.domain.ClientAdaCoreProp;

/**
 * @author wanghe
 */
public final class ExecutorBuilder {

    private ClientAdaCoreProp clientAdaCoreProp;

    private Class<? extends ClientHeaderAdapter> clientHeaderAdapter;

    private Class<?> responseCls;

    private Class<? extends DefaultClientAdaResponseFactory> responseFactory;

    private DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda;

    public ExecutorBuilder(ClientAdaCoreProp clientAdaCoreProp, Class<? extends ClientHeaderAdapter> clientHeaderAdapter, Class<?> responseCls, Class<? extends DefaultClientAdaResponseFactory> responseFactory, DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda) {
        this.clientAdaCoreProp = clientAdaCoreProp;
        this.clientHeaderAdapter = clientHeaderAdapter;
        this.responseCls = responseCls;
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

    public Class<?> getResponseCls() {
        return responseCls;
    }

    public void setResponseCls(Class<?> responseCls) {
        this.responseCls = responseCls;
    }

    public Class<? extends DefaultClientAdaResponseFactory> getResponseFactory() {
        return responseFactory;
    }

    public void setResponseFactory(Class<? extends DefaultClientAdaResponseFactory> responseFactory) {
        this.responseFactory = responseFactory;
    }

    public DefaultClientInterfaceControllerAda getDefaultClientInterfaceControllerAda() {
        return defaultClientInterfaceControllerAda;
    }

    public void setDefaultClientInterfaceControllerAda(DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda) {
        this.defaultClientInterfaceControllerAda = defaultClientInterfaceControllerAda;
    }
}
