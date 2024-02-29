package com.clientAda4j.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * @author wanghe
 */
@XmlRootElement(name = "namespace")
@XmlAccessorType(value = XmlAccessType.FIELD)
public final class ClientAdaExternalClassProp {
    /**
     * 配置文件名称
     */
    private String propName;
    /**
     * 配置文件映射class
     */
    @XmlAttribute(name = "mappingCls", required = true)
    private String classes;
    /**
     * 配置文件
     */
    private File propFile;

    public ClientAdaExternalClassProp() {
    }

    public ClientAdaExternalClassProp(String propName, String classes) {
        this.propName = propName;
        this.classes = classes;
    }

    public String getClasses() {
        return classes;
    }

    public ClientAdaExternalClassProp setClasses(String classes) {
        this.classes = classes;
        return this;
    }

    public File getPropFile() {
        return propFile;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public void setPropFile(File propFile) {
        this.propFile = propFile;
    }

    @Override
    public String toString() {
        return "{" +
                "配置文件名称='" + propName + '\'' +
                ", 配置文件类名='" + classes + '\'' +
                ", 配置文件=" + propFile +
                '}';
    }
}
