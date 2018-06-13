package com.joeqiang.tmall.pojo;

/**
 * Created by Joe强 on 2018/5/24.
 */

import java.util.Date;
import java.util.List;

/**
 * 产品表
 */
public class Product {
    private Integer id;
    private String name;
    //商品名称
    private String subTitle;
    //原价
    private double originalPrice;
    //打折价
    private double promotePrice;
    //销量
    private Integer stock;
    private Integer cid;
    private Date createDate;
    //产品对应的图片
    private Productimage productimag;

    private Integer imageId;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    //推广图片
    private Category category;

   //产品对应的图片集
    private List<Productimage> productSingleImages;

    private List<Productimage> productDetailImages;

    private int saleCount;

    private int reviewCount;
    //数量
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Productimage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<Productimage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<Productimage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<Productimage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public Productimage getProductimag() {
        return productimag;
    }

    public void setProductimag(Productimage productimag) {
        this.productimag = productimag;
    }


    public Product() {
    }

    public Product(Integer id, String name, String subTitle, double originalPrice, double promotePrice, int stock, Integer cid, Date createDate) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.originalPrice = originalPrice;
        this.promotePrice = promotePrice;
        this.stock = stock;
        this.cid = cid;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(double promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
