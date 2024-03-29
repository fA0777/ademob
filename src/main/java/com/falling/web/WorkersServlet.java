package com.falling.web;

import com.alibaba.fastjson.JSON;
import com.falling.pojo.*;
import com.falling.service.WorkersService;
import com.falling.service.impl.WorkersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@WebServlet("/workers/*")
public class WorkersServlet extends BaseServlet {
    //创建service
    private WorkersService service = new WorkersServiceImpl();

    /**
     * 根据用户名和密码查询
     *
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
        Workers worker = service.selectWorker(name);
        //判断worker是否为null或者该用户的status是否为2
        if (worker == null||worker.getStatus()==2) {
            //为null或者该用户的status为2，不可登录
            //存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");

            //跳转到login.jsp，携带错误信息提示
            request.getRequestDispatcher("/workersLogin.jsp").forward(request, response);
        } else {
            //不为null，存在，校验密码是否正确
            String salt = worker.getSalt();
            String password1=password.trim();
            password1+=salt;
            int hashCode = password1.hashCode();
            String password2=""+hashCode;

            if (worker.getPassword().equals(password2)) {
                //密码正确
                HttpSession session = request.getSession();
                session.setAttribute("worker", worker);
                //重定向
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/workers.html");
            } else {
                //密码错误
                //存储错误信息到request
                request.setAttribute("login_msg", "用户名或密码错误");

                //跳转到login.jsp，携带错误信息提示
                request.getRequestDispatcher("/workersLogin.jsp").forward(request, response);
            }

        }
    }

    public void getWorker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers worker = (Workers) session.getAttribute("worker");
        //将worker对象转成json
        String jsonString = JSON.toJSONString(worker);
        //写数据
        //数据存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateBasicInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Workers对象
        workers = JSON.parseObject(params, Workers.class);
        workers.setManagerId(0);
        //校验是否存在用户名相同的其他用户，若已存在，输出提示，修改失败
        Workers workers1 = service.selectWorker(workers.getName());
        if (workers1!=null){
            if (workers1.getId().equals(workers.getId())) {
                //不存在，可修改
                //调用service方法
                service.updateBasicInformation(workers);
                session.setAttribute("worker", workers);
                //响应成功的标识
                response.getWriter().write("success");
            } else {
                //存在，不可修改
                response.getWriter().write("fail");
            }
        }else{
            //调用service方法
            service.updateBasicInformation(workers);
            session.setAttribute("worker", workers);
            //响应成功的标识
            response.getWriter().write("success");
        }
    }

    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        String password1 = request.getParameter("password1");
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Workers对象
        Workers worker = JSON.parseObject(params, Workers.class);
        //workers中的密码是用户的密文原密码，worker中的密码是用户修改密码时输入的原密码，password1是用户要改的新密码
        String passwordW1 = worker.getPassword().trim();
        passwordW1+=workers.getSalt();
        int hashCodeW = passwordW1.hashCode();
        String passwordW2=""+hashCodeW;
        if (passwordW2.equals(workers.getPassword())){
            //用户输入的原密码正确，可修改密码，将新密码改为密文形式
            String password2 = password1.trim();
            password2+=workers.getSalt();
            int hashCode = password2.hashCode();
            String password3=""+hashCode;
            workers.setPassword(password3);
            //调用service方法
            service.updatePassword(workers);
            //响应成功的标识
            response.getWriter().write("success");
        }else {
            //用户输入的原密码错误
            //响应失败的标识
            response.getWriter().write("fail");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁session
        request.getSession().invalidate();
        //重定向
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/workersLogin.jsp");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
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
        attendanceRecord.setWorkerId(workers.getId());
        attendanceRecord.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<AttendanceRecords> pageBean = service.selectByPageAndCondition(currentPage, pageSize, attendanceRecord);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addLeaveRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成LeaveRecords对象
        LeaveRecords leaveRecords = JSON.parseObject(params, LeaveRecords.class);
        leaveRecords.setWorkerId(workers.getId());
        leaveRecords.setApproval(0);
        leaveRecords.setStatus(1);
        //调用service方法
        service.addLeaveRecords(leaveRecords);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
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
        leaveRecords.setWorkerId(workers.getId());
        leaveRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<LeaveRecords> pageBean = service.selectByPageAndCondition2(currentPage, pageSize, leaveRecords);
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
        //将json字符串转成Workers对象
        LeaveRecords leaveRecords = JSON.parseObject(params, LeaveRecords.class);
        //判断是否已经审批，已审批则不得修改
        Integer approval = leaveRecords.getApproval();
        if (approval == 0) {
            //未审批，调用service方法
            service.updateLeaveRecords(leaveRecords);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //已审批，输出提示，不得修改
            response.getWriter().write("fail");
        }
    }

    public void deleteLeaveRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        //循环，根据id查找LeaveRecords，若有一个已审批，输出提示，不得删除
        for (int id : ids) {
            boolean b = service.selectLeaveRecords(id);
            if (b == false) {
                //已审批，该组数据不可删除
                response.getWriter().write("fail");
                return;
            }
        }
        //全都未审批，删除该组数据
        service.deleteLeaveRecords(ids);
        //响应成功的标识
        response.getWriter().write("success");
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
        //将json字符串转成TrainingActivities对象
        TrainingActivities trainingActivities = JSON.parseObject(params, TrainingActivities.class);
        trainingActivities.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivities> pageBean = service.selectByPageAndCondition3(currentPage, pageSize, trainingActivities);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addTrainingActivitiesRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivities对象
        TrainingActivities trainingActivities = JSON.parseObject(params, TrainingActivities.class);
        //验证该用户是否参与过该培训活动，若参与过，输出提示，不得再参与
        boolean b = service.selectTrainingActivitiesRecords(trainingActivities.getId(), workers.getId());
        if (b) {
            //未参与过，创建TrainingActivitiesRecords对象
            TrainingActivitiesRecords trainingActivitiesRecords = new TrainingActivitiesRecords();
            trainingActivitiesRecords.setTrainingActivityId(trainingActivities.getId());
            trainingActivitiesRecords.setManagerId(trainingActivities.getManagerId());
            trainingActivitiesRecords.setWorkerId(workers.getId());
            trainingActivitiesRecords.setProcess(0);
            trainingActivitiesRecords.setStatus(1);
            //调用service方法
            service.addTrainingActivitiesRecords(trainingActivitiesRecords);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //参与过，不得再参与
            response.getWriter().write("fail");
        }
    }

    public void selectByPageAndCondition4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成TrainingActivitiesRecords对象
        TrainingActivitiesRecords trainingActivitiesRecords = JSON.parseObject(params, TrainingActivitiesRecords.class);
        trainingActivitiesRecords.setWorkerId(workers.getId());
        trainingActivitiesRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivitiesRecords> pageBean = service.selectByPageAndCondition4(currentPage, pageSize, trainingActivitiesRecords);
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
        //循环，根据id查找TrainingActivitiesRecords，若有一个已审批，输出提示，不得删除
        for (int id : ids) {
            boolean b = service.selectTrainingActivitiesRecords2(id);
            if (b == false) {
                //已审批，该组数据不可删除
                response.getWriter().write("fail");
                return;
            }
        }
        //全都未审批，删除该组数据
        service.deleteTrainingActivitiesRecords(ids);
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
        //将json字符串转成Announcements对象
        Announcements announcements = JSON.parseObject(params, Announcements.class);
        announcements.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Announcements> pageBean = service.selectByPageAndCondition5(currentPage, pageSize, announcements);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition6(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
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
        resignations.setWorkerId(workers.getId());
        resignations.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Resignations> pageBean = service.selectByPageAndCondition6(currentPage, pageSize, resignations);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateResignations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Resignations对象
        Resignations resignations = JSON.parseObject(params, Resignations.class);
        //判断是否已审批，已审批不得修改，未审批可修改
        if (resignations.getApproval() == 0) {
            //未审批
            //调用service方法
            service.updateResignation(resignations);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //已审批
            response.getWriter().write("fail");
        }

    }

    public void deleteResignations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        //循环，根据id查找Resignations，若有一个已审批，输出提示，不得删除
        for (int id : ids) {
            boolean b = service.selectResignations(id);
            if (b == false) {
                //已审批，该组数据不可删除
                response.getWriter().write("fail");
                return;
            }
        }
        //全都未审批，删除该组数据
        service.deleteResignations(ids);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void addResignations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Resignations对象
        Resignations resignations = JSON.parseObject(params, Resignations.class);
        resignations.setApproval(0);
        resignations.setWorkerId(workers.getId());
        resignations.setStatus(1);
        //判断该员工是否存在未审批的离职申请，若存在，则不得再申请
        Resignations _resignations = service.selectResignations2(workers.getId(), 0, 1);
        if (_resignations == null) {
            //不存在，可申请
            //调用service方法
            service.addResignations(resignations);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //已审批
            response.getWriter().write("fail");
        }
    }

    public void selectByPageAndCondition7(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收参数：当前页码，每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        //获取查询条件对象
        //接受查询条件数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成SalaryRecords对象
        SalaryRecords salaryRecords = JSON.parseObject(params, SalaryRecords.class);
        salaryRecords.setWorkerId(workers.getId());
        salaryRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<SalaryRecords> pageBean = service.selectByPageAndCondition7(currentPage, pageSize, salaryRecords);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取员工对象
        HttpSession session = request.getSession();
        Workers workers = (Workers) session.getAttribute("worker");
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成Workers对象
        workers = JSON.parseObject(params, Workers.class);
        workers.setManagerId(0);
        String profilePhoto = workers.getProfilePhoto();
        String s = profilePhoto.replaceAll("fakepath", "img");
        String s1 = s.replaceAll("C", "D");
        //图片命名
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String s2 = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        FileInputStream fis = new FileInputStream(s1);
        FileOutputStream fos = new FileOutputStream("src\\main\\webapp\\img\\" + s2+".jpeg");
        int len;
        byte[] bytes = new byte[1024 * 1024 * 5];
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fos.close();
        fis.close();
        workers.setProfilePhoto("img\\" + s2+".jpeg");
        //调用service方法
        service.uploadProfilePhoto(workers);
        session.setAttribute("worker", workers);
        //响应成功的标识
        response.getWriter().write("success");
    }

}
