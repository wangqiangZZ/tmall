package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.ProductMapper;
import com.joeqiang.tmall.pojo.*;
import com.joeqiang.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/27.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public Product getProduct(Integer pid) {
        return productMapper.getProduct(pid);
    }

    @Override
    public Category getCategorybyId(Integer cid) {
        return productMapper.getCategorybyId(cid);
    }

    @Override
    public Integer getProductImageById(Integer pid) {
        return productMapper.getProductImageById(pid);
    }

    @Override
    public List<Integer> getProductSaleCount(Integer pid) {
        return productMapper.getProductSaleCount(pid);
    }

    @Override
    public Integer getProductReviewById(Integer pid) {
        return productMapper.getProductReviewById(pid);
    }

    @Override
    public List<Productimage> productSingleImages(Integer pid) {
        return productMapper.productSingleImages(pid);
    }

    @Override
    public List<Review> getReviewcontent(Integer pid) {
        return productMapper.getReviewcontent(pid);
    }

    @Override
    public List<Propertyvalue> productParamterList(Integer pid) {

        List<Propertyvalue> productParamterList = productMapper.productParamterList(pid);

        for (Propertyvalue propertyvalue : productParamterList) {

            Integer ptid = propertyvalue.getPtid();
            Property properties = productMapper.productParamterName(ptid);
            propertyvalue.setProperty(properties);
        }


        return productParamterList;
    }


    @Override
    public List<Productimage> productDetailImages(Integer pid) {
        return productMapper. productDetailImages(pid);
    }

    @Override
    public List<Product> buyOne(Integer pid) {
        return productMapper.buyOne(pid);
    }

    @Override
    public Integer getImage(Integer pid) {
        return productMapper.getImage(pid);
    }

    @Override
    public List<Orderltem> getOrderitem(Integer uid) {
        return productMapper.getOrderitem(uid);
    }

    @Override
    public Integer update(Integer num, Integer pid) {
        return productMapper.update(num,pid);
    }

    @Override
    public Integer add(Integer pid,Integer uid,Integer num) {
        return productMapper.add(pid,uid,num);
    }

    @Override
    public Integer getPid(int oid) {
        return productMapper.getPid(oid);
    }

    @Override
    public Integer getUid(String name, String password) {
        return productMapper.getUid(name,password);
    }


}
