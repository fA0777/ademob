package com.falling.service.impl;

import com.falling.mapper.ManagersMapper;
import com.falling.mapper.WorkersMapper;
import com.falling.pojo.*;
import com.falling.service.WorkersService;
import com.falling.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class WorkersServiceImpl implements WorkersService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Workers selectWorker(String name) {
        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取WorkersMapper
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //调用方法
        Workers worker = mapper.selectWorker(name);
        //释放资源
        sqlSession.close();
        return worker;
    }

    @Override
    public void updateBasicInformation(Workers workers) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.updateBasicInformation(workers);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void updatePassword(Workers workers) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.updatePassword(workers);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void uploadProfilePhoto(Workers workers) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.uploadProfilePhoto(workers);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<AttendanceRecords> selectByPageAndCondition(int currentPage, int pageSize, AttendanceRecords attendanceRecord) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<AttendanceRecords> rows = mapper.selectByPageAndCondition(begin, size, attendanceRecord);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(attendanceRecord);
        //封装为PageBean对象
        PageBean<AttendanceRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void addLeaveRecords(LeaveRecords leaveRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.addLeaveRecords(leaveRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<LeaveRecords> selectByPageAndCondition2(int currentPage, int pageSize, LeaveRecords leaveRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<LeaveRecords> rows = mapper.selectByPageAndCondition2(begin, size, leaveRecords);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition2(leaveRecords);
        //封装为PageBean对象
        PageBean<LeaveRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateLeaveRecords(LeaveRecords leaveRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.updateLeaveRecords(leaveRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public boolean selectLeaveRecords(Integer id) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        LeaveRecords leaveRecords = mapper.selectLeaveRecords(id);
        if (leaveRecords.getApproval()==0){
            //未审批，可删除
            return true;
        }else {
            //已审批，不可删除
            return false;
        }
    }

    @Override
    public void deleteLeaveRecords(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.deleteLeaveRecords(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public boolean selectTrainingActivitiesRecords(Integer trainingActivityId, Integer workerId) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        TrainingActivitiesRecords trainingActivitiesRecords = mapper.selectTrainingActivitiesRecords(trainingActivityId, workerId);
        if (trainingActivitiesRecords==null){
           //该员工未报名参与过该培训活动，可报名参与
            return true;
        }else {
            //该员工已报名参与过该培训活动，不可报名参与
            return false;
        }
    }

    @Override
    public void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.addTrainingActivitiesRecords(trainingActivitiesRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<TrainingActivities> selectByPageAndCondition3(int currentPage, int pageSize, TrainingActivities trainingActivities) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //处理TrainingActivities条件数据，模糊表达式
        String trainingContent = trainingActivities.getTrainingContent();
        if (trainingContent!=null&&trainingContent.length()>0){
            trainingActivities.setTrainingContent("%"+trainingContent+"%");
        }
        //调用方法查询当前页数据
        List<TrainingActivities> rows = mapper.selectByPageAndCondition5(begin, size, trainingActivities);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition5(trainingActivities);
        //封装为PageBean对象
        PageBean<TrainingActivities> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<TrainingActivitiesRecords> selectByPageAndCondition4(int currentPage, int pageSize, TrainingActivitiesRecords trainingActivitiesRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<TrainingActivitiesRecords> rows = mapper.selectByPageAndCondition4(begin, size, trainingActivitiesRecords);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition4(trainingActivitiesRecords);
        //封装为PageBean对象
        PageBean<TrainingActivitiesRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void deleteTrainingActivitiesRecords(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.deleteTrainingActivitiesRecords(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public boolean selectTrainingActivitiesRecords2(Integer id) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        TrainingActivitiesRecords trainingActivitiesRecords = mapper.selectTrainingActivitiesRecords2(id);
        if (trainingActivitiesRecords.getProcess()==0){
            //未审批，可删除
            return true;
        }else {
            //已审批，不可删除
            return false;
        }
    }

    @Override
    public PageBean<Announcements> selectByPageAndCondition5(int currentPage, int pageSize, Announcements announcements) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //处理announcements条件数据，模糊表达式
        String announcementContent = announcements.getAnnouncementContent();
        if (announcementContent!=null&&announcementContent.length()>0){
            announcements.setAnnouncementContent("%"+announcementContent+"%");
        }
        //调用方法查询当前页数据
        List<Announcements> rows = mapper.selectByPageAndCondition5(begin, size, announcements);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition5(announcements);
        //封装为PageBean对象
        PageBean<Announcements> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Resignations> selectByPageAndCondition6(int currentPage, int pageSize, Resignations resignations) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //处理Resignations条件数据，模糊表达式
        String reason = resignations.getReason();
        if (reason!=null&&reason.length()>0){
            resignations.setReason("%"+reason+"%");
        }
        //调用方法查询当前页数据
        List<Resignations> rows = mapper.selectByPageAndCondition6(begin, size, resignations);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition6(resignations);
        //封装为PageBean对象
        PageBean<Resignations> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateResignation(Resignations resignations) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.updateResignations(resignations);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteResignations(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.deleteResignations(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public boolean selectResignations(Integer id) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        Resignations resignations = mapper.selectResignations(id);
        if (resignations.getApproval()==0 && resignations.getStatus()==1){
            //未审批，可删除
            return true;
        }else {
            //已审批，不可删除
            return false;
        }
    }

    @Override
    public Resignations selectResignations2(Integer workerId,Integer approval,Integer status){
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        Resignations resignations = mapper.selectResignations2(workerId,approval,status);
        return resignations;
    }

    @Override
    public void addResignations(Resignations resignations) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        mapper.addResignations(resignations);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<SalaryRecords> selectByPageAndCondition7(int currentPage, int pageSize, SalaryRecords salaryRecords) {
        SqlSession sqlSession = factory.openSession();
        WorkersMapper mapper = sqlSession.getMapper(WorkersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<SalaryRecords> rows = mapper.selectByPageAndCondition7(begin, size, salaryRecords);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition7(salaryRecords);
        //封装为PageBean对象
        PageBean<SalaryRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }
}
