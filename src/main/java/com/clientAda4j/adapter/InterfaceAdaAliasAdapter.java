package com.clientAda4j.adapter;

import com.google.common.collect.ImmutableMap;

/**
 * 请求body适配器
 *
 * @author wanghe
 */
public interface InterfaceAdaAliasAdapter {
    /**
     * 头部别名映射
     *
     * @param headers 头部
     * @return ImmutableMap<String, Object>
     */
    ImmutableMap<String, Object> headerAlias(ImmutableMap<String, Object> headers);

    /**
     * 参数别名映射
     *
     * @param params 请求参数
     * @return ImmutableMap<String, Object>
     */
    ImmutableMap<String, Object> paramsAlias(ImmutableMap<String, Object> params);
}
