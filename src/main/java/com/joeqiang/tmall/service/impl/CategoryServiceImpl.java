package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.CategoryMapper;
import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.service.CategoryService;

import com.joeqiang.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joe强
 * @date 2018/5/22
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> list(Page page) {
        return categoryMapper.list(page);
    }
   //分页查询
    @Override
    public List<Category> paging(int startIndex, int count) {

        return categoryMapper.paging(startIndex,count);
    }
    //增加图片
    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }
   //删除
    @Override
    public void delete(Category c) {
        categoryMapper.delete(c);
    }
    //编辑
    @Override
    public Category get(Integer id) {
        return categoryMapper.get(id);
    }
    //修改
    @Override
    public int update(String name, int id) {
        return categoryMapper.update(name,id);
    }



    @Override
    public int total() {
        return categoryMapper.total();}
}

