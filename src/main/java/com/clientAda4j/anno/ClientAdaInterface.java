package com.clientAda4j.anno;

import com.clientAda4j.IClientAdaResponseFactory;
import com.clientAda4j.LinkedHashMapClientAdaResponseFactory;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientAdaInterface {
    /**
     * 接口名称
     */
    String interfaceName() default "";

    /**
     * 接口ID
     */
    String interfaceId();

    /**
     * 接口地址
     */
    String interfaceUri();

    /**
     * 返回结果处理器
     */
    Class<? extends IClientAdaResponseFactory<?>> responseFactory() default LinkedHashMapClientAdaResponseFactory.class;
}
