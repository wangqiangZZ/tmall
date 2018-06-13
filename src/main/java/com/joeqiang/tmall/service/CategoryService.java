package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.util.Page;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/22.
 */
public interface CategoryService{
    int total();
    List<Category> list(Page page);
    List<Category>paging(int startIndex,int count);
    void add (Category category);

    void delete(Category c);

    Category get(Integer id);

    int update(String name,int id);
}

