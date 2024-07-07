package com.clientAda4j.component;

import com.clientAda4j.process.ClientHeaderAdapter;
import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import com.clientAda4j.process.IClientAdaResponseFactory;
import com.clientAda4j.process.IClientHeaderAdapter;
import com.clientAda4j.domain.ClientAdaCoreProp;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public final class AnnotationPointCut extends AnnotationPointCutExecutor {
    @Autowired
    private DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda;

    // 处理类上面的注解ClientAdaComponent
    @Around(value = "@within(clientAdaComponent)")
    private Object clientAdaComponentPoint(ProceedingJoinPoint currentPoint, ClientAdaComponent clientAdaComponent) throws Throwable {
        return this.process(currentPoint, clientAdaComponent);
    }

    //处理方法上面的注解ClientAdaInterface
    @Around(value = "@annotation(clientAdaInterface)")
    private Object clientAdaInterfacePoint(ProceedingJoinPoint currentPoint, ClientAdaInterface clientAdaInterface) throws Throwable {
        return this.process(currentPoint, clientAdaInterface);
    }

    /**
     * 获取请求执行器构建对象
     *
     * @return ExecutorBuilder
     */
    public ExecutorBuilder getExecutorObjBuilder() {
        return new ExecutorBuilder(this.clientAdaCoreProp, this.clientHeaderAdapter, this.responseFactory, this.defaultClientInterfaceControllerAda);
    }

    public final static class ExecutorBuilder {

        private ClientAdaCoreProp clientAdaCoreProp;

        private Class<? extends IClientHeaderAdapter> clientHeaderAdapter;

        private Class<? extends IClientAdaResponseFactory<?>> responseFactory;

        private DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda;

        public ExecutorBuilder(ClientAdaCoreProp clientAdaCoreProp, Class<? extends IClientHeaderAdapter> clientHeaderAdapter, Class<? extends IClientAdaResponseFactory<?>> responseFactory, DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda) {
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

        public Class<? extends IClientHeaderAdapter> getClientHeaderAdapter() {
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
}