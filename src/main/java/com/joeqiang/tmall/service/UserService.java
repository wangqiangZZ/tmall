package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.User;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/24.
 */
public interface UserService {

    int total();
    List<User>paging(int startIndex,int count);
}
