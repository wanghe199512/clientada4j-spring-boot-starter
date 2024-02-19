package com.clientAda4j.adapter;

import com.google.common.collect.ImmutableMap;

/**
 * 请求body适配器
 *
 * @author wanghe
 */
public interface InterfaceAdaAliasAdapter {
    /**
     * headers参数映射
     *
     * @param headers 头部
     * @return ImmutableMap<String, Object>
     */
    ImmutableMap<String, Object> headerAlias(ImmutableMap<String, Object> headers);

    /**
     * params参数映射
     *
     * @param params 参数
     * @return ImmutableMap<String, Object>
     */
    default ImmutableMap<String, Object> paramsAlias(ImmutableMap<String, Object> params) {
        return ImmutableMap.of("body", params);
    }
}
