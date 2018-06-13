package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.Order;
import com.joeqiang.tmall.pojo.Orderltem;
import com.joeqiang.tmall.pojo.Product;
import com.joeqiang.tmall.pojo.User;
import com.joeqiang.tmall.service.OrderService;
import com.joeqiang.tmall.service.ProductService;
import com.joeqiang.tmall.service.RegisterService;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Joe强 on 2018/5/28.
 */

/**
 * 点击购买或者加入购物车就会弹出这个模态对话框
 * <p>
 * 在这个模态对话框上可以进行登录操作，如果账号和密码出错会出现 "账号密码错误" 提示
 *
 * @ResponseBody 用于返回数据
 */
@Controller
public class ForeController {
    @Autowired
    RegisterService registerService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String forecheckLogin(HttpSession session, Integer pid) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return "success";
        }
        return "fail";

    }

    /**
     * 接收ajax 的请求 返回数据
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String foreloginAjax(@Param("name") String name, @Param("password") String password, HttpSession session) {
        User user = registerService.login(name, password);

        if (user != null) {
            System.out.println("登陆成功");
            session.setAttribute("user", user);
            return "success";
        }
        return "fail";
    }

    /**
     * 立即购买功能
     *
     * @param pid
     * @param num
     * @return
     */
    @RequestMapping("forebuyone")
    public String buyOne(Integer pid, Integer num, HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        boolean found = false;

        List<Orderltem> orderltemList = productService.getOrderitem(user.getId());
        Product pt = productService.getProduct(pid);

        for (Orderltem orderltem : orderltemList) {

            if (pt.getId().equals(orderltem.getPid())) {
                Integer number = orderltem.getNumber() + num;
                productService.update(number, pid);
                found = true;
                break;
            }
        }
        if (!found) {
            Orderltem ol = new Orderltem();
            ol.setPid(pid);
            ol.setUid(user.getId());
            ol.setNumber(num);
            productService.add(ol.getPid(), ol.getUid(), ol.getNumber());
        }
        List<Product> productList = productService.buyOne(pid);
        Integer image = productService.getImage(pid);
        for (Product product : productList) {
            product.setImageId(image);
            double total = product.getPromotePrice() * num;
            product.setNumber(num);
            System.out.println("打折后的商品价格:" + product.getPromotePrice() + "数量" + num + "总价" + total);
            model.addAttribute("total", total);
            session.setAttribute("total", total);
            System.out.println(("把total:") + total + ("放入Session"));
            Integer productId = product.getId();
            session.setAttribute("pid", productId);
            System.out.println(("把pid:") + productId + ("放入Session"));
        }
        model.addAttribute("ois", productList);
        return "fore/buy";


    }

    /**
     * 加入购物车
     */
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(Integer pid, Integer num, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer uid = user.getId();
        boolean found = false;
        List<Orderltem> orderltemList = productService.getOrderitem(uid);
        Product pt = productService.getProduct(pid);

        for (Orderltem orderltem : orderltemList) {

            if (pt.getId().equals(orderltem.getPid())) {
                Integer number = orderltem.getNumber() + num;
                productService.update(number, pid);
                found = true;
                break;
            }
        }
        if (!found) {
            Orderltem ol = new Orderltem();
            ol.setPid(pid);
            ol.setUid(uid);
            ol.setNumber(num);

            productService.add(ol.getPid(), ol.getUid(), ol.getNumber());
        }
        return "success";
    }

    /**
     * 我的购物车
     * 1.从Session里取出 用户的信息
     * 2.根据得到的uid,查询出用户的购物车信息
     * 3.循环购物车信息，得到用户下的产品id
     * 4.根据pid 得到产品信息 返回给前台
     */
    @RequestMapping("forecart")
    public String cart(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                List<Orderltem> orderltemList = productService.getOrderitem(user.getId());
                int sum = 0;
                for (Orderltem orderltem : orderltemList) {
                    int pid = orderltem.getPid();
                    int number = orderltem.getNumber();
                    Product pt = productService.getProduct(pid);
                    int image = productService.getImage(pid);
                    pt.setImageId(image);
                    pt.setNumber(number);
                    sum += number;
                    orderltem.setPt(pt);
                }
                model.addAttribute("cartTotalItemNumber", sum);
                model.addAttribute("ois", orderltemList);
            } else {

                System.out.println("Error:没有差选到用户信息！请登陆....");
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误日志:" + e.getMessage());
        }
        return "fore/cart";
    }

    /**
     * 我的订单
     * 1.获取登陆的用户信息,为空 提示登陆
     * 2.获取用户下的订单
     * 3.产品信息
     */
    @RequestMapping("forebought")
    public String foreBought(HttpSession session, Model model) {
        try {
            User user = (User) session.getAttribute("user");
            Integer uid = user.getId();
            List<Order> orderList = orderService.getOrderList(uid);
            for (Order order : orderList) {
                Integer oid = order.getId();
                Product productByoid = orderService.getProductByoid(oid);
                Integer pid = productByoid.getId();
                Integer image = productService.getImage(pid);
                productByoid.setImageId(image);

                List<Orderltem> orderltemList = new ArrayList<>();
                Orderltem orderltem = new Orderltem();
                orderltem.setPt(productByoid);
                orderltemList.add(orderltem);
                order.setOrderItems(orderltemList);

                model.addAttribute("os", orderList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fore/bought";
    }

    /**
     * 提交订单
     */
    @RequestMapping("forecreateOrder")
    public String createOrder(Order order, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer uid = user.getId();
        String address = order.getAddress();
        String post = order.getPost();
        String userMessage = order.getUserMessage();
        String receiver = order.getReceiver();
        String mobile = order.getMobile();

        SimpleDateFormat slm = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String createDate = slm.format(new Date());
        String orderCode = new SimpleDateFormat("yyyyMMdd").format(new Date()) + RandomUtils.nextInt(10000);
        System.out.println("订单orderCode:" + orderCode);
        String status = orderService.waitPay;
        Integer pid = (Integer) session.getAttribute("pid");
        System.out.println("从Session中取出 pid:" + pid);
        boolean b = orderService.addOrder(orderCode, address, post, receiver, mobile, userMessage, createDate, uid, status, pid);
        if (!b) {
            throw new RuntimeException();
        }
        Integer oid = orderService.orderId(orderCode);

        orderService.updateOrderitem(oid, pid);
        System.out.println("订单更新成功...");


        double total = (double) session.getAttribute("total");
        System.out.println("从Session中取出 total:" + total);
        model.addAttribute("total", total);
        model.addAttribute("oid", oid);
        return "fore/alipay";
    }

    /**
     * 付款成功
     */
    @RequestMapping("forepayed")
    public String forePayed(Integer oid, double total, Model model) {
        Order order = orderService.getOrder(oid);
        String status = orderService.waitDelivery;
        orderService.updateOrder(status, oid);
        model.addAttribute("o", order);
        model.addAttribute("total", total);
        return "fore/payed";
    }

    /**
     * 购物车 删除功能
     *
     * @return
     */
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Integer oiid) {
        Integer a = orderService.deleteOrderItem(oiid);
        Integer b = orderService.deleteOrder_(oiid);
        if (a > 0 & b > 0) {
            return "success";
        }

        return "fail";

    }

    /**
     * 结算功能
     *
     * @param oiid
     * @return
     */

    @RequestMapping("forebuy")
    public String forebuy(String oiid, Model model, HttpSession session) {

        try {

            User user = (User) session.getAttribute("user");
            if (user != null) {
                Integer userId = user.getId();
                String[] oid = oiid.split(",");
                double sum = 0;
                for (int i = 0; i < oid.length; i++) {
                    System.out.println(oid[i]);
                    Integer id = Integer.parseInt(oid[i]);
                    Integer num = orderService.getNum(id, userId);
                    Double productTotal = orderService.getProductTotal(id);
                    double b = productTotal * num;
                    System.out.println("商品价格:" + productTotal);
                    sum += b;
                }
                model.addAttribute("total", sum);
            }

            System.out.println("未检测到用户信息....");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("errorLog:" + e.getMessage());
        }
        return "fore/alipay";
    }
}

