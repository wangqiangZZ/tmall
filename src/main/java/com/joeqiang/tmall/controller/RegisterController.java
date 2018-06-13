package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.Product;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.HomePageService;
import com.joeqiang.tmall.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/26.
 */

/**
 * 注册页面
 */
@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    HomePageService homePageService;

    @RequestMapping("foreregister")
    public String UserRegister(Model model, String name ,String password) {

        //转义防止恶意攻击
//         name = HtmlUtils.htmlEscape("name");
        System.out.println("name=====>>>>" + name);
        boolean b = registerService.registerUser(name, password);
        System.out.println("true 为用户已存在:" + b);
        if (b) {
            String msg = "用户名已存在";

            model.addAttribute("msg", msg);
            model.addAttribute("user", null);
            return "fore/register";
        }
        registerService.insertUser(name, password);

        return "fore/registerSuccess";

    }

    /**
     * 登陆
     */
    @RequestMapping("forelogin")
    public String login(Model model, User user,HttpSession session) {
        String name = user.getName();
        String password = user.getPassword();
       user = registerService.login(name, password);
        if (user!=null) {
            List<Category> categoryList = homePageService.get();
            Integer cid;
            try {
                /**
                 * 跳转首页
                 */
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
            System.out.println(user.getName()+"用户信息");
            session.setAttribute("user",user);

            model.addAttribute("user",user);
            model.addAttribute("cs", categoryList);
            return "fore/home";


        }
        String msg = "用户名或密码错误";
        model.addAttribute("msg", msg);
        model.addAttribute("user",user);
        return "fore/login";

    }
    /**
     * 退出
     */
   @RequestMapping("forelogout")
    public String loginOut(HttpSession session){
       session.removeAttribute("user");

       return "redirect:/forehome";
   }


}
