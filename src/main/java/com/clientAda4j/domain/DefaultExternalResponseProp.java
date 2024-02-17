package com.clientAda4j.domain;

/**
 * 响应结构
 *
 * @author wanghe
 */
public class DefaultExternalResponseProp {

    private int code;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public DefaultExternalResponseProp(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DefaultExternalResponseProp setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DefaultExternalResponseProp setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public DefaultExternalResponseProp setData(Object data) {
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
