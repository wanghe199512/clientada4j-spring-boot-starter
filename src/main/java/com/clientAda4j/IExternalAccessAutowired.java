package com.clientAda4j;

import com.clientAda4j.domain.ExternalProp;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 三方数据接入
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
public interface IExternalAccessAutowired<T> {
    /**
     * 加载类路径文件
     *
     * @param file 文件
     * @param cls  隐射关系实体
     * @return E
     * @throws JAXBException JAXBException
     */
    <E> E loaderClassPathFile(File file, Class<? extends ExternalProp> cls) throws JAXBException;

    /**
     * 加载类路径文件
     *
     * @param classPath classPath
     * @param cls       隐射关系实体
     * @return E
     * @throws JAXBException JAXBException
     */
    <E> E loaderClassPathFile(String classPath, Class<? extends ExternalProp> cls) throws FileNotFoundException, JAXBException;

    /**
     * 获取所有配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    ImmutableList<T> getInterfaceProp();

    /**
     * 使用Id获取配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    T getInterfaceProp(String externalId);

    /**
     * 使用Id获取配置并转换为实际的类接收
     * @param cls cls
     * @param externalId 接口ID
     * @return ImmutableList<DefaultExternalProp>
     */
    T getInterfaceProp(String externalId, Class<? extends ExternalProp> cls);

    /**
     * 获取请求参数适配器
     *
     * @return InterfaceAdaAliasAdapter<T>
     */
    InterfaceAdaAliasAdapter getRequestMappingAdaAliasAdapter();

    /**
     * 添加请求参数适配器
     *
     * @param adapter adapter
     * @return AbstractExternalInterfaceAda<T>
     */
    IExternalAccessAutowired<T> addRequestMappingAliasAdapter(InterfaceAdaAliasAdapter adapter);
}
