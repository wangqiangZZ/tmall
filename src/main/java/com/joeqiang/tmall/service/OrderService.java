package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/24.
 */
public interface OrderService {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    int  total();

    List<Order> paging(int startIndex, int count);

   List<Order> getOrderList(Integer uid);

    User getUserName(Integer uid);

    List<Orderltem> getOrderltemList(Integer uid);

    List<Product>getProductList(Integer uid);

    Productimage getProductImg(Integer pid);

    Integer  getProductNum(Integer pid,Integer uid);

    float getPromotePrice(Integer pid);

    Integer delivery(String newTime,String status,Integer id);

    List<Orderltem> getUserOrderltem (Integer uid);

    boolean addOrder(String orderCode,String address,
                     String post, String receiver, String mobile,
                     String userMessage,String createDate,
                     Integer uid,String status,Integer pid);

    Integer orderId(String orderCode);

    Order getOrder(Integer oid);

    Integer updateOrder(String status,Integer oid);

    Integer updateOrderitem(Integer oid, Integer pid);

    Product getProductByoid(Integer oid);

    Integer  deleteOrderItem(Integer oiid);

    Integer deleteOrder_(Integer oiid);

    Double getProductTotal(Integer id);

    Integer getNum(Integer id, Integer userId);
}
