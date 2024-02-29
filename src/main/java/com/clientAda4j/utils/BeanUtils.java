package com.clientAda4j.utils;

import java.util.Objects;

/**
 * 对象工具
 *
 * @author wanghe
 */
public class BeanUtils {

    public static Class<?> loaderClasses(String clsName) {
        try {
            return Class.forName(clsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
