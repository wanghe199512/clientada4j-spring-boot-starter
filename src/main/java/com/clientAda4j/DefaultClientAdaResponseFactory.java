package com.clientAda4j;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.clientAda4j.domain.DefaultClientResponseProp;

/**
 * 返回结果处理器
 *
 * @author wanghe
 */
public class DefaultClientAdaResponseFactory<E> implements IClientAdaResponseFactory<E> {
    @Override
    public E process(String response) {
        return JSON.parseObject(response, new TypeReference<E>() {
        });
    }
}
