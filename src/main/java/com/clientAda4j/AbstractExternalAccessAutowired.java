package com.clientAda4j;

import cn.hutool.core.bean.BeanUtil;
import com.clientAda4j.adapter.DefaultInterfaceAdaAliasAdapter;
import com.clientAda4j.adapter.InterfaceAdaAliasAdapter;
import com.clientAda4j.domain.ExternalProp;
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
public abstract class AbstractExternalAccessAutowired implements IExternalAccessAutowired, Serializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 配置文件路径
     */
    protected String loadPropPath;
    /**
     * 映射参数对象
     */
    protected Map<String, HashMap<String, Object>> mappingProps = new HashMap<String, HashMap<String, Object>>();

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
                    HashMap<String, Object> unmarshalProp = this.loaderClassPathFile(resource.getFile());
                    this.mappingProps.put((String) unmarshalProp.get("mappingCls"), unmarshalProp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("配置文件读取完成 {}", System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载类路径文件
     *
     * @param file 文件
     * @return E
     * @throws JAXBException JAXBException
     */
    @Override
    public HashMap<String, Object> loaderClassPathFile(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ExternalProp.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        HashMap<String, Object> unmarshalProp = (HashMap<String, Object>) BeanUtil.beanToMap(unmarshaller.unmarshal(file), false, true);
        logger.info("Preparing >> 类加载映射[{}]: \n[{}] ",unmarshalProp.get("mappingCls"), unmarshalProp.toString());
        return unmarshalProp;
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
    public HashMap<String, Object> loaderClassPathFile(String classPath, Class<? extends ExternalProp> cls) throws FileNotFoundException, JAXBException {
        return this.loaderClassPathFile(ResourceUtils.getFile(classPath));
    }


    /**
     * 设置配置文件路径
     *
     * @param definedPath 路径
     * @return this
     */
    public AbstractExternalAccessAutowired setPropertiesPath(String definedPath) {
        this.loadPropPath = definedPath;
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

    public Map<String, HashMap<String, Object>> getMappingProps() {
        return mappingProps;
    }
}