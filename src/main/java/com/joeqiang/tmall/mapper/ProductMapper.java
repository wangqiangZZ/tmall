package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/27.
 */
public interface ProductMapper {

    Product getProduct(Integer pid);

    Category getCategorybyId(Integer cid);

    Integer getProductImageById(Integer pid);

    List<Integer> getProductSaleCount(Integer pid);


    Integer getProductReviewById(Integer pid);

    List<Productimage> productSingleImages(Integer pid);

    List<Review> getReviewcontent(Integer pid);

    List<Propertyvalue> productParamterList(Integer pid);

   Property productParamterName(Integer ptid);

    List<Productimage> productDetailImages(Integer pid);

    List<Product> selectByExample(ProductExample example);

    List<Product> buyOne(Integer pid);

    Integer getImage(Integer pid);

    List<Orderltem> getOrderitem(Integer uid);

    Integer update(@Param("num") Integer num,@Param("pid") Integer pid);

  Integer add(@Param("pid") Integer pid,@Param("uid") Integer uid,@Param("num") Integer num);

    Integer getPid(int oid);

    Integer getUid(@Param("name") String name,@Param("password") String password);
}
