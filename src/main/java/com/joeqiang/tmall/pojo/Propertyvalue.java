package com.joeqiang.tmall.pojo;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/28.
 */
public class Propertyvalue {
    private Integer id;
    private Integer pid;
    private Integer ptid;
    private String value;
    //产品属性
   private Property property;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
