package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/24.
 */
public interface UserMapper {

    List<User> getUserList();
    int total();
    List<User>paging(@Param("startIndex") int startIndex,@Param("count") int count);

}
