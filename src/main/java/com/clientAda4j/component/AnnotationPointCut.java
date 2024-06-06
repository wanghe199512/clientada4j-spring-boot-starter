package com.clientAda4j.component;

import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public final class AnnotationPointCut {

    @Autowired
    private AnnotationPointCutExecutor executor;

    // 处理类上面的注解ClientAdaComponent
    @Around(value = "@within(clientAdaComponent)")
    public Object clientAdaComponentPoint(ProceedingJoinPoint currentPoint, ClientAdaComponent clientAdaComponent) throws Throwable {
        return this.executor.process(currentPoint, clientAdaComponent);
    }

    //处理方法上面的注解ClientAdaInterface
    @Around(value = "@annotation(clientAdaInterface)")
    public Object clientAdaInterfacePoint(ProceedingJoinPoint currentPoint, ClientAdaInterface clientAdaInterface) throws Throwable {
        return this.executor.process(currentPoint, clientAdaInterface);
    }

    /**
     * 获取请求执行器构建对象
     *
     * @return ExecutorBuilder
     */
    public ExecutorBuilder getExecutorBuilder() {
        return this.executor.getExecutorBuilder();
    }
}