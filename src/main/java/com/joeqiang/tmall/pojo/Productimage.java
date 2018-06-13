package com.joeqiang.tmall.pojo;

/**
 * Created by Joe强 on 2018/5/25.
 */

import java.util.List;

/**
 * 产品图片表
 */
public class Productimage {
    private Integer id;
    private Integer pid;
    private String type;

    public Productimage() {
    }

    public Productimage(Integer id, Integer pid, String type) {
        this.id = id;
        this.pid = pid;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
