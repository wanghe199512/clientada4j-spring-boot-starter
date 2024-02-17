package com.clientAda4j.domain;

/**
 * 响应结构
 *
 * @author wanghe
 */
public class ExternalResponseProp<T> {

    private T response;

    public T getResponse() {
        return response;
    }

    public ExternalResponseProp() {
    }

    public ExternalResponseProp(T response) {
        this.response = response;
    }

    public ExternalResponseProp<T> setResponse(T response) {
        this.response = response;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "response=" + response +
                '}';
    }

}
