package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.*;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/27.
 */
public interface ProductService {
    Product getProduct(Integer pid);

    Category getCategorybyId(Integer cid);

    Integer getProductImageById(Integer pid);

    List<Integer> getProductSaleCount(Integer pid);

     Integer getProductReviewById(Integer pid);

    List<Productimage> productSingleImages(Integer pid);

    List<Review> getReviewcontent(Integer pid);

    List<Propertyvalue> productParamterList(Integer pid);

    List<Productimage> productDetailImages(Integer pid);

    List<Product> buyOne(Integer pid);

    Integer getImage(Integer pid);

    List<Orderltem> getOrderitem(Integer uid);

    Integer update(Integer num, Integer pid);

    Integer add(Integer pid,Integer uid,Integer num);

    Integer getPid(int oid);

    Integer getUid(String name, String password);
}
