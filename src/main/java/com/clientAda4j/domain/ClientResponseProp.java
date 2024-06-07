package com.clientAda4j.domain;

/**
 * 响应结构
 *
 * @author wanghe
 */
public class ClientResponseProp<T> {

    private T response;

    public T getResponse() {
        return response;
    }

    public ClientResponseProp() {
    }

    public ClientResponseProp(T response) {
        this.response = response;
    }

    public ClientResponseProp<T> setResponse(T response) {
        this.response = response;
        return this;
    }

    @Override
    public String toString() {
        return "[ClientAda SDK] Preparing: >> 请求响应 " + response;
    }

}
