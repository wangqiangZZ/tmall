package com.joeqiang.tmall.service.impl;

import com.joeqiang.tmall.mapper.HomePageMapper;
import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.Product;
import com.joeqiang.tmall.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/28.
 */
@Service
public class HomePageServiceImpl implements HomePageService{
    @Autowired
    HomePageMapper homePageMapper;
    @Override
    public List<Category> get() {
        return homePageMapper.get();
    }

    @Override
    public List<Product>getProduct(Integer cid) {
        return homePageMapper.getProduct(cid);
    }

    @Override
    public Integer productImage(Integer pid) {
        return homePageMapper.productImage(pid);
    }

    @Override
    public List<Product> search(String keyword) {
        return homePageMapper.search(keyword);
    }

    @Override
    public Integer getImagesByPid(Integer pid) {
        return homePageMapper.getImageByPid(pid);
    }

    @Override
    public Integer productSaleCount(Integer pid) {

     List<Integer> saleCount = homePageMapper.productSaleCount(pid);
        Integer sum=0;
        for (Integer sc : saleCount) {
            sum+=sc;
        }
        return sum;
    }
}
