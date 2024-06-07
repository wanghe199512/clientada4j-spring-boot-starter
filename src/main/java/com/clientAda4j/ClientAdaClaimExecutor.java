package com.clientAda4j;

import com.alibaba.fastjson2.JSON;
import com.clientAda4j.component.AnnotationPointCut;
import com.clientAda4j.component.ExecutorBuilder;
import com.clientAda4j.domain.ClientResponseProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
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

    /**
     * 基于响应工厂的执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    @Override
    public ClientResponseProp<?> executeResponseFactory(Object args) {
        ExecutorBuilder executorObj = this.executor.getExecutorBuilder();
        try {
            if (Objects.isNull(executorObj.getResponseFactory())) {
                throw new ClientAdaExecuteException("[ClientAda SDK] 基于响应工厂的执行函数，必须配置在注解@ClientAdaInterface中配置responseFactory");
            }
            return executorObj.getDefaultClientInterfaceControllerAda()
                    .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), executorObj.getResponseFactory().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */

    @Override
    public <E> ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls) {
        ExecutorBuilder executorObj = this.executor.getExecutorBuilder();
        try {
            return executorObj.getDefaultClientInterfaceControllerAda()
                    .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), responseCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}