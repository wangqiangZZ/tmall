package com.joeqiang.tmall.controller;

import com.joeqiang.tmall.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截器
 */
@WebFilter(filterName = "LoginFilter", urlPatterns ={"/forecart","/forebought"} )
public class LoginFilter implements Filter {
    public void destroy() {
        System.out.println("过滤器销毁....");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/forelogin");
            System.out.println("访问地址:" + request.getContextPath());
            return;
        }
        chain.doFilter(req, resp);
        System.out.println("过滤器执行....");
    }

      public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化....");
    }

}
