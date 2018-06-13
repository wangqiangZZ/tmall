package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.User;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/26.
 */
public interface RegisterService {

    boolean registerUser(String name, String password);

    boolean insertUser(String name, String password);

    User login(String name, String password);


}
