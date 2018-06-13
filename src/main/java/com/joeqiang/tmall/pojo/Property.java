package com.joeqiang.tmall.pojo;

/**
 * Created by Joe强 on 2018/5/28.
 */

/**
 *  产品属性表
 */
public class Property {

      private  Integer id;
      private  Integer cid;
      private  String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
