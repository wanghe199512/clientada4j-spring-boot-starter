package com.clientAda4j.adapter;

import com.google.common.collect.ImmutableMap;

/**
 * 构建默认的别名映射适配器
 *
 * @author wanghe
 */
public class DefaultInterfaceAdaAliasAdapter implements InterfaceAdaAliasAdapter {
    /**
     * 头部别名映射
     *
     * @param headers 头部
     * @return ImmutableMap<String, Object>
     */
    @Override
    public ImmutableMap<String, Object> headerAlias(ImmutableMap<String, Object> headers) {
        return ImmutableMap.of("header", headers);
    }

    /**
     * 参数别名映射
     *
     * @param params 请求参数
     * @return ImmutableMap<String, Object>
     */
    @Override
    public ImmutableMap<String, Object> paramsAlias(ImmutableMap<String, Object> params) {
        return ImmutableMap.of("body", params);
    }
}