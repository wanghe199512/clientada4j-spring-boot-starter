package com.clientAda4j.adapter;

import com.google.common.collect.ImmutableMap;

/**
 * 构建默认的映射适配器
 *
 * @author wanghe
 */
public class DefaultInterfaceAdaAliasAdapter implements InterfaceAdaAliasAdapter {
    /**
     * headers参数映射
     *
     * @param headers 头部
     * @return ImmutableMap<String, Object>
     */
    @Override
    public ImmutableMap<String, Object> headerAlias(ImmutableMap<String, Object> headers) {
        return ImmutableMap.of("header", headers);
    }
}