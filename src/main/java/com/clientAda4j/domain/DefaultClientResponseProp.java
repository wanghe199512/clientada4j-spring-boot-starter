package com.clientAda4j.domain;

import java.util.HashMap;

/**
 * 响应结构
 *
 * @author wanghe
 */
public class DefaultClientResponseProp {

    private int code;

    private String message;

    private HashMap<String, Object> data;

    public int getCode() {
        return code;
    }

    public DefaultClientResponseProp(int code, String message, HashMap<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DefaultClientResponseProp setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DefaultClientResponseProp setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public DefaultClientResponseProp setData(HashMap<String, Object> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
