package com.clientAda4j;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.clientAda4j.domain.DefaultClientResponseProp;

import java.util.LinkedHashMap;

/**
 * 返回结果处理器
 *
 * @author wanghe
 */
public class LinkedHashMapClientAdaResponseFactory implements IClientAdaResponseFactory<LinkedHashMap<String, Object>> {
    @Override
    public LinkedHashMap<String, Object> process(String response) {
        return JSON.parseObject(response, new TypeReference<LinkedHashMap<String, Object>>() {});
    }
}
