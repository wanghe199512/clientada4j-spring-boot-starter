package com.clientAda4j.component;

import com.clientAda4j.ClientHeaderAdapter;
import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientInterfaceProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientAdaAnnotationPointCutExecutor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ClientAdaCoreProp clientAdaCoreProp;

    private Class<? extends ClientHeaderAdapter> clientHeaderAdapter;

    /**
     * 处理方法注解
     */
    public Object process(ProceedingJoinPoint currentPoint, ClientAdaComponent clientAdaComponent) throws Throwable {
        this.clientAdaCoreProp = new ClientAdaCoreProp().setClientId(clientAdaCoreProp.getClientId()).setClientName(clientAdaComponent.clientName())
                .setClientPort(clientAdaComponent.clientPort()).setClientUri(clientAdaComponent.clientUrl());
        this.clientHeaderAdapter = clientAdaComponent.clientHeaderAdapter();
        return currentPoint.proceed();
    }

    /**
     * 处理接口注解
     */
    public Object process(ProceedingJoinPoint currentPoint, ClientAdaInterface clientAdaInterface) throws Throwable {
        if (StringUtils.isEmpty(clientAdaInterface.interfaceId()) && StringUtils.isEmpty(clientAdaInterface.interfaceName())) {
            throw new ClientAdaExecuteException("[执行器] interfaceId和interfaceName 不能同时为空，请检查参数....");
        }
        this.clientAdaCoreProp.setClientInterface(new ClientInterfaceProp(clientAdaInterface.interfaceName(), clientAdaInterface.interfaceId(), clientAdaInterface.interfaceUri()));
        return currentPoint.proceed();
    }

    /**
     * 获取方法执行参数
     */

    private Object[] getProceedingJoinPointArgs(ProceedingJoinPoint currentPoint) {
        Object[] args = currentPoint.getArgs();
        logger.info("[执行器] @@{}@@ args={} finished！", currentPoint.getSignature().getDeclaringTypeName(), args);
        return args;
    }

}