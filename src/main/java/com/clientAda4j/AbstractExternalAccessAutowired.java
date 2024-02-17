package com.clientAda4j;

import com.clientAda4j.adapter.DefaultInterfaceAdaAliasAdapter;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.clientAda4j.domain.ExternalProp;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

/**
 * 自动装配
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
public abstract class AbstractExternalAccessAutowired<T extends ExternalProp> implements IExternalAccessAutowired<T>, Serializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 配置文件路径
     */
    protected String loadPropPath;
    /**
     * 解析结果实体
     */
    protected final Set<T> props = new LinkedHashSet<>();
    /**
     * 映射参数对象
     */
    protected ImmutableMap<String, Class<? extends ExternalProp>> mapping = new ImmutableMap.Builder<String, Class<? extends ExternalProp>>().build();

    /**
     * 请求参数适配器
     */
    protected InterfaceAdaAliasAdapter adapter = new DefaultInterfaceAdaAliasAdapter();

    /**
     * 加载配置文件
     */
    protected void loaderFile() {
        try {
            if (StringUtils.isEmpty(this.loadPropPath)) {
                throw new RuntimeException(String.format("未找到三方系统接口接入文档 [%s],加载配置失败...", this.loadPropPath));
            }
            LinkedList<Resource> resources = new LinkedList<>(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(this.loadPropPath)));
            logger.info("Preparing >>> [已找到配置: {}] 开始读取配置文件 {}", resources.size(), resources.toString());
            try {
                for (Resource resource : resources) {
                    if (!resource.getFile().getName().toLowerCase().endsWith(".xml")) {
                        continue;
                    }
                    Class<? extends ExternalProp> cls = this.mapping.get(resource.getFilename());
                    if (Objects.nonNull(cls)) {
                        this.props.add(this.loaderClassPathFile(resource.getFile(), cls));
                        continue;
                    }
                    this.props.add(this.loaderClassPathFile(resource.getFile(), ExternalProp.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("配置文件读取完成 {}",  System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载类路径文件
     *
     * @param file 文件
     * @param cls  隐射关系实体
     * @return E
     * @throws JAXBException JAXBException
     */
    @Override
    public <E> E loaderClassPathFile(File file, Class<? extends ExternalProp> cls) throws JAXBException {
        if (Objects.isNull(cls)) {
            throw new RuntimeException("三方系统接入配置 >>> Class对象为空");
        }
        JAXBContext context = JAXBContext.newInstance(ExternalProp.class, cls);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        logger.info("Preparing >> [类加载映射: {}] \n[映射参数: {}] ", cls.getName(), unmarshaller.unmarshal(file));
        return (E) unmarshaller.unmarshal(file);
    }

    /**
     * 加载类路径文件
     *
     * @param classPath classPath
     * @param cls       隐射关系实体
     * @return E
     * @throws JAXBException JAXBException
     */
    @Override
    public <E> E loaderClassPathFile(String classPath, Class<? extends ExternalProp> cls) throws FileNotFoundException, JAXBException {
        return this.loaderClassPathFile(ResourceUtils.getFile(classPath), cls);
    }


    /**
     * 设置配置文件路径
     *
     * @param definedPath 路径
     * @return this
     */
    public AbstractExternalAccessAutowired<T> setPropertiesPath(String definedPath) {
        this.loadPropPath = definedPath;
        return this;
    }

    public DefaultExternalAccessAutowiredService<T> build() {
        return new DefaultExternalAccessAutowiredService<T>().build();
    }

    /**
     * 设置文件与实体映射
     *
     * @param mapping mappingList
     * @return this
     */
    public AbstractExternalAccessAutowired<T> mappingCls(ImmutableMap<String, Class<? extends ExternalProp>> mapping) {
        if (Objects.nonNull(mapping) && !mapping.isEmpty()) {
            this.mapping = mapping;
        }
        return this;
    }

    /**
     * 添加请求映射适配器
     *
     * @param adapter adapter
     * @return AbstractExternalInterfaceAda<T>
     */
    @Override
    public AbstractExternalAccessAutowired<T> addRequestMappingAliasAdapter(InterfaceAdaAliasAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 获取请求映射参数适配器
     *
     * @return InterfaceAdaAliasAdapter<T>
     */
    @Override
    public InterfaceAdaAliasAdapter getRequestMappingAdaAliasAdapter() {
        return this.adapter;
    }

    public String loadPropPath() {
        return loadPropPath;
    }

    public Set<T> getProps() {
        return props;
    }

}