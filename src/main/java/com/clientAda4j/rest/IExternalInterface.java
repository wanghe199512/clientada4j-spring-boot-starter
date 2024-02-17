package com.clientAda4j.rest;

import com.clientAda4j.domain.DefaultExternalResponseProp;
import com.clientAda4j.domain.ExternalProp;
import com.clientAda4j.domain.ExternalResponseProp;
import com.google.common.collect.ImmutableMap;
import org.apache.http.Header;

public interface IExternalInterface<T extends ExternalProp> {
    /**
     * 设置请求header
     *
     * @param header header
     * @param <H>    header
     * @return IExternalInterfaceAda<T>
     */
    <H extends Header> IExternalInterface<T> addClientHeaders(H[] header);

    /**
     * 请求接口
     *
     * @param externalId 接口ID
     * @param serviceCd  请求子服务code
     * @param params     请求参数
     */
    ExternalResponseProp<DefaultExternalResponseProp> request(String externalId, String serviceCd, ImmutableMap<String, Object> params);

    /**
     * 请求接口
     *
     * @param domainUrl 主URL
     * @param params    请求参数
     */
    ExternalResponseProp<ImmutableMap<String, Object>> request(String domainUrl, ImmutableMap<String, Object> params);


    /**
     * 请求接口
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param header    请求头
     * @param params    请求参数
     */
    String request(T prop, String serviceCd, ImmutableMap<String, Object> header, ImmutableMap<String, Object> params);

    /**
     * 请求接口
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param params    请求参数
     */
    ExternalResponseProp<DefaultExternalResponseProp> request(T prop, String serviceCd, ImmutableMap<String, Object> params);

}
