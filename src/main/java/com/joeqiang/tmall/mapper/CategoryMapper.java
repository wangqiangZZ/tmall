package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.util.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Created by Joe强 on 2018/5/22.
 */

public interface CategoryMapper {
    //分页查询
    List<Category> list(Page page);

    //查询需要分页的总数目
    Integer total();

    //分页查询
    List<Category> paging(@Param("startIndex") int startIndex, @Param("count") int count);

    //增加图片
    void add(Category category);

    //删除图片
    void delete(Category c);
   //编辑
    Category get(Integer id);
    //修改
    Integer update(@Param("name") String name,@Param("id") Integer id);
}

