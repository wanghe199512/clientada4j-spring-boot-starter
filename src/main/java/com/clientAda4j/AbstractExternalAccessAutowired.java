package com.clientAda4j;

import com.clientAda4j.adapter.DefaultInterfaceAdaAliasAdapter;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.clientAda4j.domain.ClientAdaExternalClassProp;
import com.clientAda4j.domain.ExternalProp;
import com.clientAda4j.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * 自动装配
 *
 * @author wanghe
 * @email 1280381827@qq.com
 */
public abstract class AbstractExternalAccessAutowired implements IExternalAccessAutowired, Serializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 配置文件路径
     */
    protected String propertiesPath;
    /**
     * 映射参数对象
     */
    protected List<ClientAdaExternalClassProp> mappingProps = new ArrayList<>();
    /**
     * 请求参数对象
     */
    protected List<ExternalProp> requestProps = new ArrayList<>();

    /**
     * 请求参数适配器
     */
    protected InterfaceAdaAliasAdapter adapter = new DefaultInterfaceAdaAliasAdapter();

    /**
     * 加载配置文件
     */
    protected void loaderFile() {
        try {
            if (StringUtils.isEmpty(this.propertiesPath)) {
                throw new RuntimeException(String.format("未找到三方系统接口接入文档 [%s],加载配置失败...", this.propertiesPath));
            }
            LinkedList<Resource> resources = new LinkedList<>(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(this.propertiesPath)));
            logger.info("Preparing >>> [已找到配置: {}] 开始读取配置文件 {}", resources.size(), resources.toString());
            try {
                for (Resource resource : resources) {
                    if (!resource.getFile().getName().toLowerCase().endsWith(".xml")) {
                        continue;
                    }
                    this.mappingProps.add(this.loaderClientAdaClsMappingProp(resource.getFile()));
                }
                this.loaderClientAdaClsRequestProp();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("配置文件读取完成 {}", System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载映射参数对象
     *
     * @param file 需要加载的文件
     * @return ClientAdaExternalClassProp
     * @throws JAXBException JAXBException
     */
    public final ClientAdaExternalClassProp loaderClientAdaClsMappingProp(File file) throws JAXBException {
        ClientAdaExternalClassProp externalClassProp = (ClientAdaExternalClassProp) this.getJaxbUnmarshaller(ClientAdaExternalClassProp.class, ClientAdaExternalClassProp.class).unmarshal(file);
        externalClassProp.setPropName(file.getName());
        externalClassProp.setPropFile(file);
        logger.info("Preparing >> [映射参数对象] 已加载:[{}] ", externalClassProp.toString());
        return externalClassProp;
    }

    /**
     * 加载请求参数对象
     */
    @Override
    public void loaderClientAdaClsRequestProp() throws JAXBException {
        for (ClientAdaExternalClassProp classProp : this.mappingProps) {
            Class<?> classes = BeanUtils.loaderClasses(classProp.getClasses());
            if (!(BeanUtils.getInstance(classes) instanceof ExternalProp)) {
                continue;
            }
            ExternalProp externalProp = (ExternalProp) Objects.requireNonNull(this.getJaxbUnmarshaller(ExternalProp.class, classes)).unmarshal(classProp.getPropFile());
            this.requestProps.add(externalProp);
            logger.info("Preparing >> [请求参数对象] 已加载:[{}] ", externalProp.toString());
        }
    }


    /**
     * 设置配置文件路径
     *
     * @param definedPath 路径
     * @return this
     */
    public AbstractExternalAccessAutowired setPropertiesPath(String definedPath) {
        this.propertiesPath = definedPath;
        return this;
    }

    public DefaultExternalAccessAutowiredService build() {
        return new DefaultExternalAccessAutowiredService().build();
    }

    /**
     * 添加请求映射适配器
     *
     * @param adapter adapter
     * @return AbstractExternalInterfaceAda<T>
     */
    @Override
    public AbstractExternalAccessAutowired addRequestMappingAliasAdapter(InterfaceAdaAliasAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 获取 Unmarshaller
     *
     * @param cls    cls 父类
     * @param subCls subCls 子类
     * @return Unmarshaller
     */
    private Unmarshaller getJaxbUnmarshaller(Class<?> cls, Class<?> subCls) {
        try {
            JAXBContext context = JAXBContext.newInstance(cls, subCls);
            return context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取请求映射参数适配器
     *
     * @return InterfaceAdaAliasAdapter<T>
     */
    @Override
    public final InterfaceAdaAliasAdapter getRequestMappingAdaAliasAdapter() {
        return this.adapter;
    }

    public String loadPropPath() {
        return propertiesPath;
    }

    public List<ClientAdaExternalClassProp> getMappingProps() {
        return mappingProps;
    }
}