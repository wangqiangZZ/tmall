package com.joeqiang.tmall.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Joe强 on 2018/5/27
 * 因为jsp 文件不是放在src下的不能直接访问，这个类相当于中转站
 *
 */
@Controller
public class PageConterller {
    @RequestMapping("register")
    public String register() {
        return "fore/register";
    }

    @RequestMapping("loginPage")
    public String login(){
        return "fore/login";
    }

    @RequestMapping("product")
    public String homePage(){
        return "fore/product";
    }

   @RequestMapping("category")
    public String category(){
        return "fore/category";
   }

    @RequestMapping("buy")
    public String buy(){
        return "fore/buy";
    }

    @RequestMapping("cartPage")
    public String cart (){
        return "fore/cart";
    }

    @RequestMapping("page")
    public String page (){
        return "vjpage/page";
    }

    @RequestMapping("page2")
    public String page2(){
        return "vjpage/page2";
    }
}