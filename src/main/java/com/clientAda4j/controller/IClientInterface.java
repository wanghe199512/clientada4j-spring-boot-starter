package com.clientAda4j.controller;

import com.clientAda4j.process.IClientAdaResponseFactory;
import com.clientAda4j.process.IClientHeaderAdapter;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientHeaderProp;
import com.clientAda4j.domain.ClientResponseProp;
import com.clientAda4j.domain.DefaultClientResponseProp;
import com.google.common.collect.ImmutableMap;
import org.apache.http.HttpEntity;

import java.util.LinkedHashMap;

public interface IClientInterface {
    /**
     * 设置请求header
     *
     * @param clientHeaderProp header
     * @return DefaultInterfaceClientAda
     */
    DefaultClientInterfaceControllerAda addClientHeaders(ClientHeaderProp clientHeaderProp);

    /**
     * 自定义请求header
     *
     * @param e ClientAdaHeaderAdapter
     * @return DefaultInterfaceClientAda
     */
    <E extends IClientHeaderAdapter> DefaultClientInterfaceControllerAda addClientHeadersAdapter(E e);

    /**
     * 请求接口
     *
     * @param clientUrl    请求url
     * @param interfaceUri 接口url
     * @param params       请求参数
     */
    ClientResponseProp<LinkedHashMap<String, Object>> request(String clientUrl, String interfaceUri, ImmutableMap<String, Object> params);


    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param params            请求参数
     */
    ClientResponseProp<DefaultClientResponseProp> request(ClientAdaCoreProp clientAdaCoreProp, ImmutableMap<String, Object> params);

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param requestObj        请求对象
     * @param cls               响应参数转换为实际对象
     * @param <E>               实际参数对象
     */
    <E> ClientResponseProp<E> request(ClientAdaCoreProp clientAdaCoreProp, HttpEntity requestObj, Class<E> cls);

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param requestObj        请求对象
     * @param factory           IClientAdaResponseFactory
     * @param <E>               实际参数对象
     */
    <E> ClientResponseProp<E> request(ClientAdaCoreProp clientAdaCoreProp, HttpEntity requestObj, IClientAdaResponseFactory<E> factory);

}
