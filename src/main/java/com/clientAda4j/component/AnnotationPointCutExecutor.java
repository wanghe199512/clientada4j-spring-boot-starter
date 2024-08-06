package com.clientAda4j.component;

import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.process.IClientAdaResponseFactory;
import com.clientAda4j.process.IClientHeaderAdapter;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientInterfaceProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Objects;

/**
 * 注解端点执行器
 *
 * @author wanghe
 */
public abstract class AnnotationPointCutExecutor {
    /**
     * 核心请求参数对象
     */
    protected ClientAdaCoreProp clientAdaCoreProp;
    /**
     * 自定义头部处理器
     */
    protected Class<? extends IClientHeaderAdapter> clientHeaderAdapter;
    /**
     * 响应工厂
     */
    protected Class<? extends IClientAdaResponseFactory<?>> responseFactory;

    /**
     * 处理类注解
     */
    protected Object processMethod(ProceedingJoinPoint currentPoint, ClientAdaComponent clientAdaComponent) throws Throwable {
        this.clientAdaCoreProp = new ClientAdaCoreProp().setClientId(clientAdaComponent.clientId()).setClientName(clientAdaComponent.clientName())
                .setClientPort(clientAdaComponent.clientPort()).setClientUri(clientAdaComponent.clientUrl());
        if (StringUtils.isEmpty(this.clientAdaCoreProp.getClientUri())) {
            throw new ClientAdaExecuteException("没有获取到有效的[客户端URI],进程终止..");
        }
        this.clientHeaderAdapter = clientAdaComponent.clientHeaderAdapter();
        return currentPoint.proceed();
    }

    /**
     * 处理接口注解
     */
    protected Object processInterface(ProceedingJoinPoint currentPoint, ClientAdaInterface clientAdaInterface) throws Throwable {
        if (StringUtils.isEmpty(clientAdaInterface.interfaceId()) && StringUtils.isEmpty(clientAdaInterface.interfaceName())) {
            throw new ClientAdaExecuteException("interfaceId和interfaceName不能同时为空");
        }
        if (Objects.isNull(this.clientAdaCoreProp)) {
            throw new ClientAdaExecuteException("未找到@ClientAdaComponent注解或@ClientAdaComponent,执行失败!!");
        }
        this.responseFactory = clientAdaInterface.responseFactory();
        this.clientAdaCoreProp.setClientInterface(new ClientInterfaceProp(clientAdaInterface.interfaceName(), clientAdaInterface.interfaceId(), clientAdaInterface.interfaceUri()));
        return currentPoint.proceed();
    }
}