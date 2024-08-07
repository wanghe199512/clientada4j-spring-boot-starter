package com.clientAda4j;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.clientAda4j.component.AnnotationPointCut;
import com.clientAda4j.component.ClientAdaEnvironment;
import com.clientAda4j.domain.ClientResponseProp;
import com.clientAda4j.exeption.ClientAdaExecuteException;
import com.google.common.collect.ImmutableMap;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
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
        AnnotationPointCut.ExecutorBuilder executorObj = this.executor.getExecutorObjBuilder();
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
        AnnotationPointCut.ExecutorBuilder executorObj = this.executor.getExecutorObjBuilder();
        try {
            return executorObj.getDefaultClientInterfaceControllerAda()
                    .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), responseCls);
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
    public <E> ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls, ClientAdaEnvironment environment) {
        AnnotationPointCut.ExecutorBuilder executorObj = this.executor.getExecutorObjBuilder();
        environment.env(executorObj.getClientAdaCoreProp());
        try {
            return executorObj.getDefaultClientInterfaceControllerAda()
                    .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(executorObj.getClientAdaCoreProp(), new StringEntity(JSON.toJSONString(args)), responseCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 自定义执行可直接请求的API
     *
     * @param domainUrl 主请求地址
     * @param args      请求参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    @Override
    public ClientResponseProp<LinkedHashMap<String, Object>> execute(String domainUrl, ImmutableMap<String, Object> args) {
        AnnotationPointCut.ExecutorBuilder executorObj = this.executor.getExecutorObjBuilder();
        try {
            return executorObj.getDefaultClientInterfaceControllerAda()
                    .addClientHeadersAdapter(executorObj.getClientHeaderAdapter().newInstance()).request(domainUrl, "", args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义执行可直接请求的API
     *
     * @param domainUrl   主请求地址
     * @param args        请求参数
     * @param responseCls 指定返回实体
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    @Override
    public <E> ClientResponseProp<E> execute(String domainUrl, ImmutableMap<String, Object> args, Class<E> responseCls) {
        return new ClientResponseProp<E>(BeanUtil.toBean(this.execute(domainUrl, args), responseCls));
    }

}