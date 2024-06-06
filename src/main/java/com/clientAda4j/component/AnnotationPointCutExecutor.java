package com.clientAda4j.component;

import com.alibaba.fastjson2.JSON;
import com.clientAda4j.ClientHeaderAdapter;
import com.clientAda4j.DefaultClientAdaResponseFactory;
import com.clientAda4j.Executor;
import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientInterfaceProp;
import com.clientAda4j.domain.ClientResponseProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 注解端点执行器
 *
 * @author wanghe
 */
@Component
public class AnnotationPointCutExecutor implements Executor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 核心请求参数对象
     */
    private ClientAdaCoreProp clientAdaCoreProp;
    /**
     * 自定义头部处理器
     */
    private Class<? extends ClientHeaderAdapter> clientHeaderAdapter;
    /**
     * 请求响应Class
     */
    private Class<?> responseCls;
    /**
     * 响应工厂
     */
    private Class<? extends DefaultClientAdaResponseFactory> responseFactory;

    @Autowired
    private DefaultClientInterfaceControllerAda defaultClientInterfaceControllerAda;

    /**
     * 处理方法注解
     */
    public Object process(ProceedingJoinPoint currentPoint, ClientAdaComponent clientAdaComponent) throws Throwable {
        this.clientAdaCoreProp = new ClientAdaCoreProp().setClientId(clientAdaComponent.clientId()).setClientName(clientAdaComponent.clientName())
                .setClientPort(clientAdaComponent.clientPort()).setClientUri(clientAdaComponent.clientUrl());
        this.clientHeaderAdapter = clientAdaComponent.clientHeaderAdapter();
        return currentPoint.proceed();
    }

    /**
     * 处理接口注解
     */
    public Object process(ProceedingJoinPoint currentPoint, ClientAdaInterface clientAdaInterface) throws Throwable {
        if (StringUtils.isEmpty(clientAdaInterface.interfaceId()) && StringUtils.isEmpty(clientAdaInterface.interfaceName())) {
            throw new ClientAdaExecuteException("interfaceId和interfaceName不能同时为空");
        }
        if (Objects.isNull(this.clientAdaCoreProp)) {
            throw new ClientAdaExecuteException("未找到@ClientAdaComponent注解或@ClientAdaComponent,执行失败!!");
        }
        this.responseFactory = clientAdaInterface.responseFactory();
        this.responseCls = clientAdaInterface.responseCls();
        this.clientAdaCoreProp.setClientInterface(new ClientInterfaceProp(clientAdaInterface.interfaceName(), clientAdaInterface.interfaceId(), clientAdaInterface.interfaceUri()));
        return currentPoint.proceed();
    }

    /**
     * 请求执行
     */
    @Override
    public ClientResponseProp<?> execute(Object proceedingArgs) {
        try {
            logger.info("[ClientAda SDK] 请求参数 >>> {}", proceedingArgs);
            if (Objects.nonNull(this.responseFactory)) {
                return this.defaultClientInterfaceControllerAda.addClientHeadersAdapter(
                        this.clientHeaderAdapter.newInstance()).request(this.clientAdaCoreProp, new StringEntity(JSON.toJSONString(proceedingArgs)), this.responseFactory.newInstance());
            } else {
                return this.defaultClientInterfaceControllerAda.addClientHeadersAdapter(
                        this.clientHeaderAdapter.newInstance()).request(this.clientAdaCoreProp, new StringEntity(JSON.toJSONString(proceedingArgs)), this.responseCls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}