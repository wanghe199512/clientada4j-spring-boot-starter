package com.clientAda4j.component;

import com.clientAda4j.Executor;
import com.clientAda4j.domain.ClientResponseProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注解端点执行器
 *
 * @author wanghe
 */
@Component
public class ClientAdaRequestExecutor implements Executor {
    @Autowired
    private AnnotationPointCutExecutor executor;

    @Override
    public ClientResponseProp<?> execute(Object args) {
        return executor.execute(args);
    }

}