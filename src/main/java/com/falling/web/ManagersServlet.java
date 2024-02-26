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
import java.util.List;

@WebServlet("/managers/*")
public class ManagersServlet extends BaseServlet {
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
        //校验用户名是否已存在，若已存在，输出提示，添加员工失败
        Workers workers = service.selectWorker(worker.getName());
        if (workers == null) {
            //用户名不存在，可添加
            worker.setProfilePhoto("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
            worker.setStatus(1);
            worker.setManagerId(manager.getId());
            //调用service方法
            service.addWorker(worker);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //用户名存在，不可添加
            response.getWriter().write("fail");
        }

    }

    public void deleteWorkers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] workersIds = JSON.parseObject(params, int[].class);
        Integer managerId = manager.getId();
        //将该员工相关的数据都软删除
        service.deleteAttendanceRecordsK(workersIds, managerId);
        service.deleteLeaveRecordsK(workersIds, managerId);
        service.deleteResignationsK(workersIds, managerId);
        service.deleteSalaryRecordsK(workersIds, managerId);
        service.deleteTrainingActivitiesRecordsK(workersIds, managerId);
        service.deleteWorkersK(workersIds, managerId);
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
        worker.setManagerId(0);

        //校验是否存在用户名相同的其他用户，若已存在，输出提示，修改失败
        Workers workers = service.selectWorker(worker.getName());
        if (workers.getId().equals(worker.getId())) {
            //不存在，可修改
            //调用service方法
            service.updateWorker(worker);
            //响应成功的标识
            response.getWriter().write("success");
        } else {
            //存在，不可修改
            response.getWriter().write("fail");
        }


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
        worker.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Workers> pageBean = service.selectByPageAndCondition(currentPage, pageSize, worker);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addAttendanceRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成AttendanceRecords对象
        AttendanceRecords attendanceRecord = JSON.parseObject(params, AttendanceRecords.class);
        attendanceRecord.setStatus(1);
        attendanceRecord.setManagerId(manager.getId());
        //调用service方法
        service.addAttendanceRecord(attendanceRecord);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteAttendanceRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteAttendanceRecords(ids, managerId);
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
        attendanceRecord.setManagerId(manager.getId());
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
        attendanceRecord.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<AttendanceRecords> pageBean = service.selectByPageAndCondition2(currentPage, pageSize, attendanceRecord);
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
        leaveRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<LeaveRecords> pageBean = service.selectByPageAndCondition3(currentPage, pageSize, leaveRecords);
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
        resignations.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<Resignations> pageBean = service.selectByPageAndCondition4(currentPage, pageSize, resignations);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteLeaveRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteLeaveRecords(ids, managerId);
        //响应成功的标识
        response.getWriter().write("success");
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

    public void deleteResignations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteResignations(ids, managerId);
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
        trainingActivities.setStatus(1);
        trainingActivities.setManagerId(manager.getId());
        //调用service方法
        service.addTrainingActivities(trainingActivities);
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteTrainingActivities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id数组
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成int类型数组
        int[] ids = JSON.parseObject(params, int[].class);
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteTrainingActivities(ids, managerId);
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
        trainingActivities.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivities> pageBean = service.selectByPageAndCondition5(currentPage, pageSize, trainingActivities);
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
        trainingActivitiesRecords.setStatus(1);
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
        trainingActivitiesRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<TrainingActivitiesRecords> pageBean = service.selectByPageAndCondition6(currentPage, pageSize, trainingActivitiesRecords);
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
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteTrainingActivitiesRecords(ids, managerId);
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
        announcements.setStatus(1);
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
        Integer managerId = manager.getId();
        //调用service方法
        service.deleteAnnouncements(ids, managerId);
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
        announcements.setStatus(1);
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

    public void addSalaryRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据
        BufferedReader br = request.getReader();
        //获取json字符串
        String params = br.readLine();
        //将json字符串转成SalaryRecords对象,有年、月
        SalaryRecords salaryRecords = JSON.parseObject(params, SalaryRecords.class);
        Integer year = salaryRecords.getYear();
        Integer month = salaryRecords.getMonth();
        //从数据库中查询每个员工的id，底薪，奖金（培训活动）、罚款（出勤、请假），封装为SalaryRecords对象，设置相应的数据
        Integer managerId = manager.getId();
        List<Workers> workers = service.selectWorkersF();
        for (Workers worker : workers) {
            Double bonus = new Double(0);
            Double fine = new Double(0);
            Double totalSalary = new Double(0);
            //获取员工id，底薪
            Integer workerId = worker.getId();
            Double basicSalary = worker.getBasicSalary();
            totalSalary += basicSalary;
            //查询该员工的培训活动记录中process为3的数据，获取奖金数据，并将该条记录process设为4
            List<TrainingActivitiesRecords> trainingActivitiesRecords = service.selectTrainingActivitiesRecordsF(workerId);
            for (TrainingActivitiesRecords trainingActivitiesRecord : trainingActivitiesRecords) {
                bonus += trainingActivitiesRecord.getBonus();
                trainingActivitiesRecord.setProcess(4);
                trainingActivitiesRecord.setManagerId(managerId);
                service.updateTrainingActivitiesRecordsF(trainingActivitiesRecord);
            }
            totalSalary += bonus;
            //改进：出勤和请假记录像培训活动记录一样，设process和status两个属性，这样才不会在结算工资时将相应数据都软删除了
            //查询该员工的出勤记录中status为1的数据，获取罚款数据，并将该条记录status设为2
            List<AttendanceRecords> attendanceRecords = service.selectAttendanceRecordsF(workerId);
            for (AttendanceRecords attendanceRecord : attendanceRecords) {
                fine += attendanceRecord.getFine();
                attendanceRecord.setStatus(2);
                attendanceRecord.setManagerId(managerId);
                service.updateAttendanceRecordsF(attendanceRecord);
            }
            //查询该员工的请假记录中status为1的数据，获取罚款数据，并将该条记录status设为2
            List<LeaveRecords> leaveRecords = service.selectLeaveRecordsF(workerId);
            for (LeaveRecords leaveRecord : leaveRecords) {
                fine += leaveRecord.getFine();
                leaveRecord.setStatus(2);
                leaveRecord.setManagerId(managerId);
                service.updateLeaveRecordsF(leaveRecord);
            }
            totalSalary -= fine;
            //封装为SalaryRecords对象，设置相应的数据
            SalaryRecords salaryRecords1 = new SalaryRecords();
            salaryRecords1.setWorkerId(workerId);
            salaryRecords1.setYear(year);
            salaryRecords1.setMonth(month);
            salaryRecords1.setBasicSalary(basicSalary);
            salaryRecords1.setBonus(bonus);
            salaryRecords1.setFine(fine);
            salaryRecords1.setTotalSalary(totalSalary);
            salaryRecords1.setStatus(1);
            salaryRecords1.setManagerId(managerId);
            //存入数据库
            service.addSalaryRecords(salaryRecords1);
        }
        //响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition8(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        salaryRecords.setStatus(1);
        //将数据转为int类型
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //调用service进行查询
        PageBean<SalaryRecords> pageBean = service.selectByPageAndCondition8(currentPage, pageSize, salaryRecords);
        //把pageBean对象转为json
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        //列表数据，存在中文，要设置
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


}
