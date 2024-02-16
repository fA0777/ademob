package com.falling.web;

import com.alibaba.fastjson.JSON;
import com.falling.pojo.Workers;
import com.falling.service.WorkersService;
import com.falling.service.impl.WorkersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/workers/*")
public class WorkersServlet extends BaseServlet{
    //创建service
    private WorkersService service=new WorkersServiceImpl();
    Workers worker;

    /**
     * 根据用户名和密码查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //调用service完成查询
        Workers _worker = service.selectWorker(name, password);
        //判断worker是否为null
        boolean flag=_worker==null;
        if (flag){
            //为null，不存在，不可登录
            //存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误");

            //跳转到login.jsp，携带错误信息提示
            request.getRequestDispatcher("/workersLogin.jsp").forward(request,response);
        }else{
            //不为null，存在，可登录
            worker=_worker;
            HttpSession session = request.getSession();
            session.setAttribute("worker",worker);
            //重定向
            String contextPath=request.getContextPath();
            response.sendRedirect(contextPath+"/workers.html");
        }
    }

    public void getWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get worker");
        System.out.println(worker);
        //将worker对象转成json
        String jsonString = JSON.toJSONString(worker);
        //写数据
        //数据存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    }
