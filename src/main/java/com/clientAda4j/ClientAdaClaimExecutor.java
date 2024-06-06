package com.clientAda4j;

import com.alibaba.fastjson2.JSON;
import com.clientAda4j.component.AnnotationPointCut;
import com.clientAda4j.component.ExecutorBuilder;
import com.clientAda4j.domain.ClientResponseProp;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 请求执行器
 *
 * @author wanghe
 */
@Component
public class ClientAdaClaimExecutor implements Executor {
    @Autowired
    private AnnotationPointCut executor;

    @Override
    public ClientResponseProp<?> execute(Object args) {
        ExecutorBuilder executorObj = this.executor.getExecutorBuilder();
        try {
            if (Objects.nonNull(executorObj.getResponseFactory())) {
                return executorObj.getDefaultClientInterfaceControllerAda()
                        .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), executorObj.getResponseFactory().newInstance());
            } else {
                return executorObj.getDefaultClientInterfaceControllerAda()
                        .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), executorObj.getResponseCls());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}