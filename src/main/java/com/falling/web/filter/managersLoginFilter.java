package com.falling.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/managers/*")
public class managersLoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断用户访问的是否是与登录与注册相关的资源
        String[] urls = {"/managersLogin.jsp", "/imgs/", "/css/", "/managers/selectManager"};

        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();

        //循环判断
        for (String s : urls) {
            if (url.contains(s)) {
                //找到了，放行
                chain.doFilter(request, response);
                return;
            }
        }
        //判断session中是否有manager对象
        HttpSession session = req.getSession();
        Object manager = session.getAttribute("manager");

        //判断user是否为null
        if (manager != null) {
            //session中有user，登录过了。放行
            chain.doFilter(request, response);
        } else {
            //没有登录。跳转到登录页面
            req.setAttribute("register_msg", "您尚未登录");
            req.getRequestDispatcher("/managersLogin.jsp").forward(req, response);
        }

    }
}
