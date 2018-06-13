package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.Product;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/28.
 */
public interface HomePageService {

    List<Category> get();
    List<Product> getProduct(Integer cid);

    Integer  productImage(Integer pid);

    List<Product> search(String keyword);

    Integer getImagesByPid(Integer pid);

    Integer productSaleCount(Integer pid);
}
