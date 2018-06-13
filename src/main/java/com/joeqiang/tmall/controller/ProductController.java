package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.*;
import com.joeqiang.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Joe强 on 2018/5/27.
 */

/**
 * 产品页详细信息
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("foreproduct")
    public String product(Model model, Integer pid,HttpSession session) {
        try {

            Product p = productService.getProduct(pid);
            System.out.println(" 产品 P:" + p.getStock());
            Integer cid = p.getCid();
            //推广图片
            Category category = productService.getCategorybyId(cid);
            //产品对应的单个图片
            Integer imageId = productService.getProductImageById(pid);
            System.out.println("产品图片=====》》" + imageId);
            //产品对应的销量
            List<Integer> productSaleCount = productService.getProductSaleCount(pid);
            Integer saleCount = 0;
            for (Integer orderltem : productSaleCount) {
                saleCount += orderltem;
            }
            System.out.println("产品销量=====》》" + saleCount);
            //产品对应的评价数量
            Integer review = productService.getProductReviewById(pid);
            //评价内容
            List<Review> reviewcontent = productService.getReviewcontent(pid);
            System.out.println("产品销量=====》》" + review);
            //产品详情图片集
            List<Productimage> productSingleImages = productService.productSingleImages(pid);
            List<Productimage> productDetailImages = productService.productDetailImages(pid);
            //商品详情
            List<Propertyvalue> productParamterList = productService.productParamterList(pid);
            p.setProductDetailImages(productDetailImages);
            p.setProductSingleImages(productSingleImages);
            p.setReviewCount(review);
            p.setSaleCount(saleCount);
            p.setCategory(category);
            p.setImageId(imageId);
            User user = (User) session.getAttribute("user");
            System.out.println("从Session里取出用户信息:"+user);
            model.addAttribute("pvs",  productParamterList);
            model.addAttribute("p", p);
            model.addAttribute("reviews", reviewcontent);
            model.addAttribute("user",user);
        } catch (Exception e) {

            String message = e.getMessage();
            System.out.println("错误日志" + message);
        }
        return "fore/product";
    }
}
