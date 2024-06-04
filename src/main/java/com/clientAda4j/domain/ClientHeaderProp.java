package com.clientAda4j.domain;

import com.google.common.collect.ImmutableList;
import org.apache.http.message.BasicHeader;

import java.util.List;

/**
 * 接口请求头配置
 *
 * @author wanghe
 */
public class ClientHeaderProp {

    BasicHeader[] headers;

    public ClientHeaderProp() {
    }

    public ClientHeaderProp(BasicHeader[] headers) {
        this.headers = headers;
    }

    public BasicHeader[] getHeaders() {
        return headers;
    }

    public ClientHeaderProp setHeaders(BasicHeader[] headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public String toString() {
        return "请求头信息:{" +
                "headers=" + headers +
                '}';
    }
}