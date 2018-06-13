package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.Category;
import com.joeqiang.tmall.pojo.Orderltem;
import com.joeqiang.tmall.pojo.Product;
import com.joeqiang.tmall.pojo.Productimage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/28.
 */
public interface HomePageMapper {
    List<Category> get();

    List<Product> getProduct(Integer cid);

    Integer productImage(@Param("pid") Integer pid);

    List<Product> search(String keyword);

    Integer getImageByPid(Integer pid);

    List<Integer> productSaleCount(Integer pid);
}
