package com.joeqiang.tmall.controller;


import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.service.CategoryService;
import com.joeqiang.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/22.
 */
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        System.out.println("start" + page.getStart());
        try {
            List<Category> cs = categoryService.list(page);
            int total = categoryService.total();
            page.setTotal(total);
            model.addAttribute("cs", cs);
            model.addAttribute("page", page);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误日志" + e.getMessage());
        }
        return "admin/listCategory";
    }



}
