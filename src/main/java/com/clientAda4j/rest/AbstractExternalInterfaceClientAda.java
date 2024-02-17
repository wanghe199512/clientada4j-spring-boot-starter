package com.clientAda4j.rest;

import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableMap;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractExternalInterfaceClientAda<T extends ExternalProp> implements IExternalInterface<T>, Serializable {

    /**
     * 过滤ImmutableMap中值为空数据
     *
     * @param domain 要过滤的数据
     * @return ImmutableMap<String, Object>
     */
    protected ImmutableMap<String, Object> removeNull(Map<String, Object> domain) {
        return ImmutableMap.copyOf(domain.entrySet().stream().filter((e) -> e.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }


    /**
     * 构建请求
     *
     * @param prop      接口参数
     * @param serviceCd 请求子服务code
     * @param header    请求头
     * @param params    请求参数
     * @return String
     */
    @Override
    public abstract String request(T prop, String serviceCd, ImmutableMap<String, Object> header, ImmutableMap<String, Object> params);


}
