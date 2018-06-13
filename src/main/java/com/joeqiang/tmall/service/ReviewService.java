package com.joeqiang.tmall.service;

import com.joeqiang.tmall.pojo.Review;

import java.util.List;

/**
 * Created by Joe强 on 2018/5/27.
 */
public interface ReviewService {
    void add(Review c);

    void delete(int id);
    void update(Review c);
    Review get(int id);
    List list(int pid);

    int getCount(int pid);
}
