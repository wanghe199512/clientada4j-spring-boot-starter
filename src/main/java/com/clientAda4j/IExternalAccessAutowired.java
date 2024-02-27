package com.clientAda4j;

import com.clientAda4j.domain.ExternalProp;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 三方数据接入
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
public interface IExternalAccessAutowired {
    /**
     * 加载类路径文件
     *
     * @param file 文件
     * @return E
     * @throws JAXBException JAXBException
     */
    HashMap<String,Object> loaderClassPathFile(File file) throws JAXBException;

    /**
     * 加载类路径文件
     *
     * @param classPath classPath
     * @param cls       隐射关系实体
     * @return E
     * @throws JAXBException JAXBException
     */
    HashMap<String,Object> loaderClassPathFile(String classPath, Class<? extends ExternalProp> cls) throws FileNotFoundException, JAXBException;

    /**
     * 获取所有配置
     *
     * @return ImmutableList<DefaultExternalProp>
     */
    ImmutableMap<String, Object> getInterfaceProp();

    /**
     * 使用Id获取配置收
     *
     * @param externalId 接口ID
     * @return ImmutableList<DefaultExternalProp>
     */
    Map<String,Object> getInterfaceProp(String externalId);

    /**
     * 使用Id获取配置并转换为实际的类接收
     *
     * @param cls        cls
     * @param externalId 接口ID
     * @return ImmutableList<DefaultExternalProp>
     */
    <T extends ExternalProp> T getInterfaceProp(String externalId, Class<T> cls);

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
    IExternalAccessAutowired addRequestMappingAliasAdapter(InterfaceAdaAliasAdapter adapter);
}
