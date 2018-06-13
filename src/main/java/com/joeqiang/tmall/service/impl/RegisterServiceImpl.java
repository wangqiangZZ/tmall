package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.RegisterMapper;
import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/26.
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    public RegisterMapper registerMapper;
    @Override
    public boolean registerUser(String name, String password) {

        return registerMapper.registerUser(name,password)>0? true:false;
    }

    @Override
    public boolean insertUser(String name, String password) {
        return registerMapper.insertUser(name,password)>0?true:false;
    }

    @Override
    public User login(String name, String password) {
        return registerMapper.login(name,password);
    }


}
