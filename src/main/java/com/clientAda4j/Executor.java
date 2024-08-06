package com.clientAda4j;

import com.clientAda4j.component.ClientAdaEnvironment;
import com.clientAda4j.domain.ClientResponseProp;
import com.google.common.collect.ImmutableMap;

import java.util.LinkedHashMap;

public interface Executor {
    /**
     * 默认的执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    ClientResponseProp<?> executeResponseFactory(Object args);

    /**
     * 自定义执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    <E> ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls);

    /**
     * 自定义执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    <E> ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls, ClientAdaEnvironment environment);

    /**
     * 自定义执行可直接请求的API
     *
     * @param domainUrl 主请求地址
     * @param args      请求参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    ClientResponseProp<LinkedHashMap<String, Object>> execute(String domainUrl, ImmutableMap<String, Object> args);

    /**
     * 自定义执行可直接请求的API
     *
     * @param domainUrl   主请求地址
     * @param args        请求参数
     * @param responseCls 指定返回实体
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    <E> ClientResponseProp<E> execute(String domainUrl, ImmutableMap<String, Object> args, Class<E> responseCls);
}
