package com.clientAda4j;

import com.clientAda4j.domain.ClientResponseProp;

public interface Executor {
    /**
     * 默认的执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    ClientResponseProp<?> executeResponseFactory(Object args);

    /**
     * 自定义执行
     *
     * @param args 参数
     * @return ClientResponseProp<LinkedHashMap < String, Object>>
     */
    <E> ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls);
}
