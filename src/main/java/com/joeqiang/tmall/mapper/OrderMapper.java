package com.joeqiang.tmall.mapper;

import com.joeqiang.tmall.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Joe强 on 2018/5/25.
 */
public interface OrderMapper {
    int total();

    List<Order> paging(@Param("startIndex") int startIndex, @Param("count") int count);

    List<Order> getOrderList(Integer uid);

    User getUserName(Integer uid);

    List<Orderltem> getOrderltem(@Param("uid") Integer uid);

    List<Product> getProductList(Integer uid);

    Productimage getProductImg(@Param("pid") Integer pid);

    Integer getProductNum(@Param("pid") Integer pid, @Param("uid") Integer uid);

    float getPromotePrice(@Param("pid") Integer pid);

    Integer update(@Param("newTime") String newTime, @Param("status") String status, @Param("id") Integer id);

    List<Orderltem> getUserOrderltem(Integer uid);

    Integer insetOrder(@Param("orderCode") String orderCode, @Param("address")
            String address, @Param("post")
                               String post, @Param("receiver") String receiver, @Param("mobile")
                               String mobile, @Param("userMessage") String userMessage, @Param("createDate")
                               String createDate, @Param("uid") Integer uid,
                       @Param("status") String status,@Param("pid") Integer pid);

    int orderId(String orderCode);

    Order getOrder(Integer oid);

    Integer updateOrder(@Param("status") String status, @Param("oid") Integer oid);

    Integer updateOrderitem(@Param("oid") Integer oid,@Param("pid") Integer pid);

    Product getProductByoid(Integer oid);

    Integer deleteOrderItem(Integer oiid);

    Integer deleteOrder(Integer oiid);

    Double getProductTotal(Integer id);

    Integer getNum(@Param("id") Integer id,@Param("userId") Integer userId);
}
