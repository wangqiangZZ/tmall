package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.UserMapper;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/24.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
   

    @Override
    public int total() {
        return userMapper.total();
    }

    @Override
    public List<User> paging(int startIndex, int count) {
        return userMapper.paging(startIndex,count);
    }
}
