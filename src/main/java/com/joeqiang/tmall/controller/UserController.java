package com.joeqiang.tmall.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.CategoryService;
import com.joeqiang.tmall.service.UserService;
import com.joeqiang.tmall.util.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/24.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("admin_user_list")
    public String user(Model model, Paging paging){
        Integer count = paging.getCount();
        //当前页数
        Integer start = paging.getStart();
        if (start == 0) {
            start = 1;
        }
        System.out.println("当前页数" + start);
        //起始位置
        Integer startIndex = (start - 1) * count;
        //总数
        Integer total = userService.total();
        //分页数据
        List<User> us = userService.paging(startIndex, count);
        //总共分多少页
        Integer totalCount = (int) Math.ceil(1.0 * total / count);
        paging.setTotalCount(totalCount);
        paging.setTotal(total);
        model.addAttribute("us",us);
        model.addAttribute("page",paging);

        return "admin/listUser";
    }
}
