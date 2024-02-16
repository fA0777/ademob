package com.falling.web;

import com.alibaba.fastjson.JSON;
import com.falling.pojo.*;
import com.falling.service.ManagersService;
import com.falling.service.impl.ManagersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/managers/*")
public class ManagersServlet extends BaseServlet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Managers manager;
    //创建service
    ManagersService service = new ManagersServiceImpl();


    //根据用户名和密码查询
    public void selectManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //调用service完成查询
        manager = service.selectManager(name, password);
        //判断是否为null
        boolean flag = manager == null;
        if (flag) {
            //为null，不存在，不可登录
            //存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");
            //跳转到login.jsp，携带错误信息提示
            request.getRequestDispatcher("/managersLogin.jsp").forward(request, response);
        } else {
            //不为null，存在，可登录
            //将登录成功后的Manager对象存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("manager", manager);
            //重定向
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/managers.html");

        }
    }

    public void addWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Worker对象
        Workers worker = JSON.parseObject(params, Workers.class);
        worker.setProfilePhoto("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        //调用service方法
        service.addWorker(worker);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteWorkers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String s = br.readLine();
        String[] s1 = s.split(" ");
        int[] ids = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            ids[i] = Integer.parseInt(s1[i]);
        }
        //调用service方法
        service.deleteWorkers(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void updateWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Worker对象
        Workers worker = JSON.parseObject(params, Workers.class);
        //调用service方法
        service.updateWorker(worker);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成worker对象
        Workers worker = JSON.parseObject(params, Workers.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Workers> pageBean = service.selectByPageAndCondition(currentPage, pageSize,worker);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addAttendanceRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        String workerId = request.getParameter("workerId");
        String arriveLate = request.getParameter("arriveLate");
        String leaveEarly = request.getParameter("leaveEarly");
        String absence = request.getParameter("absence");
        String fine = request.getParameter("fine");
        //封装为attendanceRecord对象
        AttendanceRecords attendanceRecord=new AttendanceRecords();
        attendanceRecord.setWorkerId(Integer.parseInt(workerId));
        if (arriveLate!=null&&arriveLate!=""){
            LocalDateTime _arriveLate = LocalDateTime.parse(arriveLate, formatter);
            attendanceRecord.setArriveLate(_arriveLate);
        }
        if (leaveEarly!=null&&leaveEarly!=""){
            LocalDateTime _leaveEarly = LocalDateTime.parse(leaveEarly, formatter);
            attendanceRecord.setArriveLate(_leaveEarly);
        }
        if (absence!=null&&absence!=""){
            LocalDateTime _absence = LocalDateTime.parse(absence, formatter);
            attendanceRecord.setAbsence(_absence);
        }
        attendanceRecord.setFine(Integer.parseInt(fine));
        //调用service方法
        service.addAttendanceRecord(attendanceRecord);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteAttendanceRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String s = br.readLine();
        String[] s1 = s.split(" ");
        int[] ids = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            ids[i] = Integer.parseInt(s1[i]);
        }
        //调用service方法
        service.deleteAttendanceRecords(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void updateAttendanceRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成AttendanceRecords对象
        AttendanceRecords attendanceRecord = JSON.parseObject(params, AttendanceRecords.class);
        //调用service方法
        service.updateAttendanceRecord(attendanceRecord);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成AttendanceRecords对象
        AttendanceRecords attendanceRecord = JSON.parseObject(params, AttendanceRecords.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<AttendanceRecords> pageBean = service.selectByPageAndCondition2(currentPage, pageSize,attendanceRecord);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成LeaveRecords对象
        LeaveRecords leaveRecords = JSON.parseObject(params, LeaveRecords.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<LeaveRecords> pageBean = service.selectByPageAndCondition3(currentPage, pageSize,leaveRecords);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Resignations对象
        Resignations resignations = JSON.parseObject(params, Resignations.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Resignations> pageBean = service.selectByPageAndCondition4(currentPage, pageSize,resignations);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateLeaveRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成LeaveRecords对象
        LeaveRecords leaveRecords = JSON.parseObject(params, LeaveRecords.class);
        leaveRecords.setManagerId(manager.getId());
        //调用service方法
        service.updateLeaveRecord(leaveRecords);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void updateResignations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Resignations对象
        Resignations resignations = JSON.parseObject(params, Resignations.class);
        resignations.setManagerId(manager.getId());
        //调用service方法
        service.updateResignation(resignations);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void addTrainingActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivities对象
        TrainingActivities trainingActivities = JSON.parseObject(params, TrainingActivities.class);
        trainingActivities.setManagerId(manager.getId());
        //调用service方法
        service.addTrainingActivities(trainingActivities);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteTrainingActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String s = br.readLine();
        String[] s1 = s.split(" ");
        int[] ids = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            ids[i] = Integer.parseInt(s1[i]);
        }
        //调用service方法
        service.deleteTrainingActivities(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivities对象
        TrainingActivities trainingActivities = JSON.parseObject(params, TrainingActivities.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivities> pageBean = service.selectByPageAndCondition5(currentPage, pageSize,trainingActivities);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateTrainingActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivities对象
        TrainingActivities trainingActivities = JSON.parseObject(params, TrainingActivities.class);
        trainingActivities.setManagerId(manager.getId());
        //调用service方法
        service.updateTrainingActivities(trainingActivities);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void addTrainingActivitiesRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivitiesRecords对象
        TrainingActivitiesRecords trainingActivitiesRecords = JSON.parseObject(params, TrainingActivitiesRecords.class);
        trainingActivitiesRecords.setManagerId(manager.getId());
        //调用service方法
        service.addTrainingActivitiesRecords(trainingActivitiesRecords);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition6(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivities对象
        TrainingActivitiesRecords trainingActivitiesRecords = JSON.parseObject(params, TrainingActivitiesRecords.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivitiesRecords> pageBean = service.selectByPageAndCondition6(currentPage, pageSize,trainingActivitiesRecords);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteTrainingActivitiesRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        //调用service方法
        service.deleteTrainingActivitiesRecords(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void updateTrainingActivitiesRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivitiesRecords对象
        TrainingActivitiesRecords trainingActivitiesRecords = JSON.parseObject(params, TrainingActivitiesRecords.class);
        trainingActivitiesRecords.setManagerId(manager.getId());
        //调用service方法
        service.updateTrainingActivitiesRecords(trainingActivitiesRecords);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void addAnnouncements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Announcements对象
        Announcements announcements = JSON.parseObject(params, Announcements.class);
        announcements.setManagerId(manager.getId());
        //调用service方法
        service.addAnnouncements(announcements);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteAnnouncements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        //调用service方法
        service.deleteAnnouncements(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void updateAnnouncements(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivitiesRecords对象
        Announcements announcements = JSON.parseObject(params, Announcements.class);
        announcements.setManagerId(manager.getId());
        //调用service方法
        service.updateAnnouncements(announcements);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition7(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Announcements对象
        Announcements announcements = JSON.parseObject(params, Announcements.class);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Announcements> pageBean = service.selectByPageAndCondition7(currentPage, pageSize, announcements);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
