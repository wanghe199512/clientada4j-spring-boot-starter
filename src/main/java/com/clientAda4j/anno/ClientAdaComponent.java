package com.clientAda4j.anno;

import com.clientAda4j.ClientAdaHeaderAdapter;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ClientAdaComponent {
    /**
     * 请求服务url
     */
    String clientUrl() default "";

    /**
     * 请求服务ID
     */
    String clientId() default "";

    /**
     * 请求服务名称
     */
    String clientName() default "";

    /**
     * 请求服务头适配器
     */
    Class<? extends ClientAdaHeaderAdapter> clientHeaderAdapter() default ClientAdaHeaderAdapter.class;
}
