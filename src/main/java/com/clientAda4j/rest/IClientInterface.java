package com.clientAda4j.rest;

import com.clientAda4j.ClientAdaHeaderAdapter;
import com.clientAda4j.domain.ClientAdaCoreProp;
import com.clientAda4j.domain.ClientHeaderProp;
import com.clientAda4j.domain.ClientResponseProp;
import com.clientAda4j.domain.DefaultClientResponseProp;
import com.google.common.collect.ImmutableMap;

import java.util.LinkedHashMap;

public interface IClientInterface {
    /**
     * 设置请求header
     *
     * @param clientHeaderProp header
     * @return DefaultInterfaceClientAda
     */
    DefaultClientInterfaceRequestAda addClientHeaders(ClientHeaderProp clientHeaderProp);

    /**
     * 自定义请求header
     *
     * @param e ClientAdaHeaderAdapter
     * @return DefaultInterfaceClientAda
     */
    <E extends ClientAdaHeaderAdapter> DefaultClientInterfaceRequestAda addClientHeadersAdapter(E e);

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

}
