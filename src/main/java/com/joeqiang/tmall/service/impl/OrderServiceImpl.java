package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.OrderMapper;
import com.joeqiang.tmall.pojo.*;
import com.joeqiang.tmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/25.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public int total() {
        return orderMapper.total();
    }

    @Override
    public List<Order> paging(int startIndex, int count) {
        return orderMapper.paging(startIndex, count);
    }

  @Override
    public List<Order> getOrderList(Integer uid) {
        return orderMapper.getOrderList(uid);
    }

    @Override
    public User getUserName(Integer uid) {
        return orderMapper.getUserName(uid);
    }

    @Override
    public List<Orderltem> getOrderltemList(Integer uid) {
        return orderMapper.getOrderltem(uid);
    }

    @Override
    public List<Product> getProductList(Integer uid) {
        return orderMapper.getProductList(uid);
    }

    @Override
    public Productimage getProductImg(Integer pid) {
        return orderMapper.getProductImg(pid);
    }

    @Override
    public Integer getProductNum(Integer pid, Integer uid) {


        return orderMapper.getProductNum(pid, uid);
    }

    @Override
    public float getPromotePrice(Integer pid) {
        return orderMapper.getPromotePrice(pid);
    }

    @Transactional(noRollbackFor = Exception.class)
    @Override
    public Integer delivery(String newTime, String status, Integer id) {
        return orderMapper.update(newTime, status, id);
    }

    @Override
    public List<Orderltem> getUserOrderltem(Integer uid) {
        return orderMapper.getUserOrderltem(uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public boolean addOrder(String orderCode, String address, String post,
                            String receiver, String mobile,
                            String userMessage, String createDate, Integer uid,
                            String status,Integer pid) {
        Integer b = orderMapper.insetOrder(orderCode, address, post, receiver,
                mobile, userMessage, createDate, uid, status,pid);
        return b > 0 ? true : false;

    }

    @Override
    public Integer orderId(String orderCode) {
        return orderMapper.orderId(orderCode);
    }

    @Override
    public Order getOrder(Integer oid) {
        return orderMapper.getOrder(oid);
    }

    @Override
    public Integer updateOrder( String status,Integer oid) {
        return  orderMapper.updateOrder(status,oid);
    }

    @Override
    public Integer updateOrderitem(Integer oid, Integer pid) {
        return orderMapper.updateOrderitem(oid,pid);
    }

    @Override
    public Product getProductByoid(Integer oid) {
        return orderMapper.getProductByoid(oid);
    }

    @Override
    public Integer deleteOrderItem(Integer oiid) {
        return orderMapper.deleteOrderItem(oiid);
    }

    @Override
    public Integer deleteOrder_(Integer oiid) {
        return orderMapper.deleteOrder(oiid);
    }

    @Override
    public Double getProductTotal(Integer id) {
        return orderMapper.getProductTotal(id);
    }

    @Override
    public Integer getNum(Integer id, Integer userId) {
        return orderMapper.getNum(id,userId);
    }


}
