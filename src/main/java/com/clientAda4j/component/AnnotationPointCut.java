package com.clientAda4j.component;

import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.controller.DefaultClientInterfaceControllerAda;
import com.clientAda4j.domain.ClientHeaderProp;
import org.apache.http.message.BasicHeader;
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
    public ExecutorBuilder getExecutorBuilder() {
        return new ExecutorBuilder(this.clientAdaCoreProp, this.clientHeaderAdapter, this.responseFactory, this.defaultClientInterfaceControllerAda);
    }
}