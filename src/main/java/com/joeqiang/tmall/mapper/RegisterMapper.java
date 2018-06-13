package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/26.
 */
public interface RegisterMapper {
    Integer registerUser(@Param("name") String name, @Param("password") String password);

    Integer insertUser(@Param("name") String name, @Param("password") String password);

   User login(@Param("name") String name, @Param("password") String password);

}
