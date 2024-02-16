package com.falling.service;

import com.falling.pojo.*;

public interface ManagersService {
    /**
     * 根据用户名和密码查询
     * @param name
     * @param password
     * @return
     */
    Managers selectManager(String name, String password);

    /**
     * 添加员工
     * @param worker
     */
    void addWorker(Workers worker);

    /**
     * 删除（开除）员工
     * @param ids
     */
    void deleteWorkers(int[] ids);

    /**
     * 修改员工信息
     * @param worker
     */
    void updateWorker(Workers worker);

    /**
     * 分页条件查询（员工信息）
     * @param currentPage
     * @param pageSize
     * @param worker
     * @return
     */
    PageBean<Workers> selectByPageAndCondition(int currentPage,int pageSize,Workers worker);

    /**
     * 添加员工出勤记录
     * @param attendanceRecord
     */
    void addAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 删除员工出勤记录
     * @param ids
     */
    void deleteAttendanceRecords(int[] ids);

    /**
     * 修改员工出勤记录
     * @param attendanceRecord
     */
    void updateAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 分页条件查询（出勤记录）
     * @param currentPage
     * @param pageSize
     * @param attendanceRecord
     * @return
     */
    PageBean<AttendanceRecords> selectByPageAndCondition2(int currentPage,int pageSize,AttendanceRecords attendanceRecord);

    /**
     * 修改员工请假申请
     * @param leaveRecords
     */
    void updateLeaveRecord(LeaveRecords leaveRecords);

    /**
     * 分页条件查询（请假申请）
     * @param currentPage
     * @param pageSize
     * @param leaveRecords
     * @return
     */
    PageBean<LeaveRecords> selectByPageAndCondition3(int currentPage, int pageSize, LeaveRecords leaveRecords);

    /**
     * 修改员工离职申请
     * @param resignations
     */
    void updateResignation(Resignations resignations);

    /**
     * 分页条件查询（离职申请）
     * @param currentPage
     * @param pageSize
     * @param resignations
     * @return
     */
    PageBean<Resignations> selectByPageAndCondition4(int currentPage, int pageSize, Resignations resignations);

    /**
     * 添加培训活动
     * @param trainingActivities
     */
    void addTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 删除培训活动
     * @param ids
     */
    void deleteTrainingActivities(int[] ids);

    /**
     * 分页条件查询（培训活动）
     * @param currentPage
     * @param pageSize
     * @param trainingActivities
     * @return
     */
    PageBean<TrainingActivities> selectByPageAndCondition5(int currentPage, int pageSize, TrainingActivities trainingActivities);

    /**
     * 修改培训活动
     * @param trainingActivities
     */
    void updateTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 添加培训活动员工记录
     * @param trainingActivitiesRecords
     */
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动员工记录
     * @param ids
     */
    void deleteTrainingActivitiesRecords(int[] ids);

    /**
     * 分页条件查询（培训活动员工记录）
     * @param currentPage
     * @param pageSize
     * @param trainingActivitiesRecords
     * @return
     */
    PageBean<TrainingActivitiesRecords> selectByPageAndCondition6(int currentPage, int pageSize, TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 修改培训活动员工记录
     * @param trainingActivitiesRecords
     */
    void updateTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 添加公告
     * @param announcements
     */
    void addAnnouncements(Announcements announcements);


    /**
     * 删除公告
     * @param ids
     */
    void deleteAnnouncements(int[] ids);

    /**
     * 修改公告
     * @param announcements
     */
    void updateAnnouncements(Announcements announcements);

    /**
     * 分页条件查询（公告）
     * @param currentPage
     * @param pageSize
     * @param announcements
     * @return
     */
    PageBean<Announcements> selectByPageAndCondition7(int currentPage, int pageSize, Announcements announcements);


}
