package com.falling.service.impl;

import com.falling.mapper.ManagersMapper;
import com.falling.pojo.*;
import com.falling.service.ManagersService;
import com.falling.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ManagersServiceImpl implements ManagersService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Managers selectManager(String name, String password) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        Managers manager = mapper.selectManager(name, password);
        sqlSession.close();
        return manager;
    }

    @Override
    public void addWorker(Workers worker) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.addWorker(worker);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteWorkers(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.deleteWorkers(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void updateWorker(Workers worker) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateWorker(worker);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<Workers> selectByPageAndCondition(int currentPage, int pageSize, Workers worker) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //处理worker条件数据，模糊表达式
        String name = worker.getName();
        if (name!=null&&name.length()>0){
            worker.setName("%"+name+"%");
        }
        String trueName = worker.getTrueName();
        if (trueName!=null&&trueName.length()>0){
            worker.setTrueName("%"+trueName+"%");
        }
        //调用方法查询当前页数据
        List<Workers> rows = mapper.selectByPageAndCondition(begin, size, worker);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(worker);
        //封装为PageBean对象
        PageBean<Workers> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void addAttendanceRecord(AttendanceRecords attendanceRecord) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.addAttendanceRecord(attendanceRecord);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteAttendanceRecords(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.deleteAttendanceRecords(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void updateAttendanceRecord(AttendanceRecords attendanceRecord) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateAttendanceRecord(attendanceRecord);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<AttendanceRecords> selectByPageAndCondition2(int currentPage, int pageSize, AttendanceRecords attendanceRecord) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<AttendanceRecords> rows = mapper.selectByPageAndCondition2(begin, size, attendanceRecord);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition2(attendanceRecord);
        //封装为PageBean对象
        PageBean<AttendanceRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateLeaveRecord(LeaveRecords leaveRecords) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateLeaveRecord(leaveRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<LeaveRecords> selectByPageAndCondition3(int currentPage, int pageSize, LeaveRecords leaveRecords) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<LeaveRecords> rows = mapper.selectByPageAndCondition3(begin, size, leaveRecords);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition3(leaveRecords);
        //封装为PageBean对象
        PageBean<LeaveRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateResignation(Resignations resignations) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateResignations(resignations);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<Resignations> selectByPageAndCondition4(int currentPage, int pageSize, Resignations resignations) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
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
        List<Resignations> rows = mapper.selectByPageAndCondition4(begin, size, resignations);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition4(resignations);
        //封装为PageBean对象
        PageBean<Resignations> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void addTrainingActivities(TrainingActivities trainingActivities) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.addTrainingActivities(trainingActivities);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteTrainingActivities(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.deleteTrainingActivities(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<TrainingActivities> selectByPageAndCondition5(int currentPage, int pageSize, TrainingActivities trainingActivities) {
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
    public void updateTrainingActivities(TrainingActivities trainingActivities) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateTrainingActivities(trainingActivities);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.addTrainingActivitiesRecords(trainingActivitiesRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteTrainingActivitiesRecords(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.deleteTrainingActivitiesRecords(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<TrainingActivitiesRecords> selectByPageAndCondition6(int currentPage, int pageSize, TrainingActivitiesRecords trainingActivitiesRecords) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        //计算开始索引=（当前页码-1）*每页显示的条数
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //调用方法查询当前页数据
        List<TrainingActivitiesRecords> rows = mapper.selectByPageAndCondition6(begin, size, trainingActivitiesRecords);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition6(trainingActivitiesRecords);
        //封装为PageBean对象
        PageBean<TrainingActivitiesRecords> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void updateTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateTrainingActivitiesRecords(trainingActivitiesRecords);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void addAnnouncements(Announcements announcements) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.addAnnouncements(announcements);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void deleteAnnouncements(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.deleteAnnouncements(ids);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public void updateAnnouncements(Announcements announcements) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
        mapper.updateAnnouncements(announcements);
        sqlSession.commit();
        sqlSession.close();
        return;
    }

    @Override
    public PageBean<Announcements> selectByPageAndCondition7(int currentPage, int pageSize, Announcements announcements) {
        SqlSession sqlSession = factory.openSession();
        ManagersMapper mapper = sqlSession.getMapper(ManagersMapper.class);
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
        List<Announcements> rows = mapper.selectByPageAndCondition7(begin, size, announcements);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition7(announcements);
        //封装为PageBean对象
        PageBean<Announcements> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //释放资源
        sqlSession.close();
        return pageBean;
    }
}
