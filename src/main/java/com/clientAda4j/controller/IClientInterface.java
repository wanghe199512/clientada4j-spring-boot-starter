package com.clientAda4j.controller;

import com.clientAda4j.ClientHeaderAdapter;
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
    <E extends ClientHeaderAdapter> DefaultClientInterfaceControllerAda addClientHeadersAdapter(E e);

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
     * @param serviceId         请求子服务code
     * @param params            请求参数
     */
    ClientResponseProp<DefaultClientResponseProp> request(ClientAdaCoreProp clientAdaCoreProp, String serviceId, ImmutableMap<String, Object> params);

    /**
     * 请求接口
     *
     * @param clientAdaCoreProp 接口参数
     * @param serviceId         请求子服务code
     * @param requestObj        请求对象
     * @param cls               响应参数转换为实际对象
     * @param <E>               实际参数对象
     */
    <E> ClientResponseProp<E> request(ClientAdaCoreProp clientAdaCoreProp, String serviceId, HttpEntity requestObj, Class<E> cls);

}
