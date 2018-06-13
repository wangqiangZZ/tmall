package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.Product;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.HomePageService;
import com.joeqiang.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/28.
 */
@Controller
public class HomePageController {
    /**
     * 首页展示
     *
     * @param model
     * @return
     */
    @Autowired
    HomePageService homePageService;
    @Autowired
    ProductService productService;
    @RequestMapping("forehome")
    public String homePage(Model model) {

        List<Category> categoryList = homePageService.get();
        Integer cid;
        try {
            for (Category category : categoryList) {
                cid = category.getId();
                System.out.println("cid" + cid);
                List<Product>   products = homePageService.getProduct(cid);
                for (Product product : products) {
                    Integer pid = product.getId();
                    System.out.println("pid" + pid);
                    Integer imageId = homePageService.productImage(pid);
                    product.setImageId(imageId);
                    System.out.println("产品对应的图片" + imageId);
                    category.setProducts(products);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常" + e.getMessage());
        }
        model.addAttribute("cs", categoryList);
        return "fore/home";
    }

    /**
     * 搜索  模糊查询
     */
    @RequestMapping("foresearch")
    public String foresearch(String keyword,Model model){
        List<Product> productList = homePageService.search(keyword);
        System.out.println("关键字"+keyword);
        for (Product product : productList) {
            Integer pid = product.getId();
            Integer imagesByPid = homePageService.getImagesByPid(pid);
            Integer saleCount = homePageService.productSaleCount(pid);
            Integer reviewById = productService.getProductReviewById(pid);
            System.out.println("模糊查询："+ product.getName());
            product.setSaleCount(saleCount);
            product.setImageId(imagesByPid);
            product.setReviewCount(reviewById);
        }

         model.addAttribute("ps",productList);

        return "fore/searchResult";
    }

}
