package com.clientAda4j;

import com.clientAda4j.component.AnnotationPointCutExecutor;
import com.clientAda4j.domain.ClientResponseProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 请求执行器
 *
 * @author wanghe
 */
@Component
public class ClientAdaClaimExecutor implements Executor {
    @Autowired
    private AnnotationPointCutExecutor executor;

    @Override
    public ClientResponseProp<?> execute(Object args) {
        return executor.execute(args);
    }

}