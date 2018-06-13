package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.*;
import com.joeqiang.tmall.service.HomePageService;
import com.joeqiang.tmall.service.OrderService;
import com.joeqiang.tmall.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/25.
 */

/**
 * 订单管理
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
     @Autowired
    HomePageService homePageService;
    @RequestMapping("admin_order_list")
    public String order(Model model, Paging paging) {

        int totalCount = 0;
        List<Order> os = null;
        try {
            Order order = new Order();
            int start = paging.getStart();
            if (start == 0) {
                start = 1;
            }
            int count = paging.getCount();
            int total = orderService.total();
            //总的分页
            totalCount = (int) Math.ceil(1.0 * total / count);
            System.out.println("totalCount" + totalCount);
            //分页的起始位置
            int startIndex = (start - 1) * count;
            os = orderService.paging(startIndex, count);

            Integer uid = null;
            //把产品，产品图片,订单详情嵌套在一起，页面循环输出
            uid = orderShow(os, order, uid);
            System.out.println("uid:" + uid);
        } catch (Exception e) {
            System.out.println("错误日志===" + e.getMessage());
        }

        paging.setTotalCount(totalCount);
        model.addAttribute("page", paging);
        model.addAttribute("os", os);
        return "admin/listOrder";
    }

    /**
     * 发货
     *
     * @param order
     * @return 当订单状态是waitDelivery的 时候，就会出现发货按钮
     * 1. 发货按钮链接跳转到admin_order_delivery
     * 2. OrderController.delivery()方法被调用
     * 3. 注入订单对象
     * 4. 修改发货时间，设置发货状态
     * 5. 更新到数据库
     * 6. 客户端跳转到admin_order_list页面
     */

    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) {
        try {
            Date date = new Date();
            long time = date.getTime();
            SimpleDateFormat slm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String newTime = slm.format(time);
            String status ="finish";
            orderService.delivery(newTime,status,order.getId());

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:admin_order_list ";
    }


    private Integer orderShow(List<Order> os, Order order, Integer uid) {
        for (Order o : os) {

            Integer pid = null;

            uid = o.getUid();

            //订单项
            List<Orderltem> orderltemList = new ArrayList<Orderltem>();
            orderltemList = orderService.getOrderltemList(uid);
            //用户下所拥有的产品
            List<Product> productList = orderService.getProductList(uid);
            for (Product product : productList) {
                pid = product.getId();
                Productimage productImg = orderService.getProductImg(pid);
                System.out.println("pimg" + productImg);
                product.setProductimag(productImg);
            }

            Orderltem orderltem = new Orderltem();
            orderltem.setProduct(productList);
            o.setOrderItems(orderltemList);
            o.setOrderltem(orderltem);
            User name = orderService.getUserName(uid);
            o.setUser(name);
            String status = o.getStatus();
            System.out.println("status:" + status);
            //将status转为中文输出
            String statusDesc = order.getStatusDesc(status);
            System.out.println("statusDesc" + statusDesc);
            o.setStatus(statusDesc);
            //商品数量
            Integer productNum = homePageService.productSaleCount(pid);
//            Integer productNum = orderService.getProductNum(pid, uid);
            System.out.println("商品数量:" + productNum);
            System.out.println("pid" + pid);
            System.out.println("uid" + uid);
            o.setTotalNumber(productNum);
            //商品价格
            float promotePrice = orderService.getPromotePrice(pid);
            o.setTotal(promotePrice);
        }
        return uid;
    }

}
