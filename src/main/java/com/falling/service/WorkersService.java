package com.falling.service;

import com.falling.pojo.*;


public interface WorkersService {
    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    Workers selectWorker(String name);

    /**
     * 修改个人基本信息
     * @param workers
     */
    void updateBasicInformation(Workers workers);


    /**
     * 修改密码
     * @param workers
     */
    void updatePassword(Workers workers);

    /**
     * 更换头像
     * @param workers
     */
    void uploadProfilePhoto(Workers workers);

    /**
     * 分页条件查询（出勤记录）
     * @param currentPage
     * @param pageSize
     * @param attendanceRecord
     * @return
     */
    PageBean<AttendanceRecords> selectByPageAndCondition(int currentPage, int pageSize, AttendanceRecords attendanceRecord);

    /**
     * 添加请假申请
     * @param leaveRecords
     */
    void addLeaveRecords(LeaveRecords leaveRecords);

    /**
     * 分页条件查询（请假申请）
     * @param currentPage
     * @param pageSize
     * @param leaveRecords
     * @return
     */
    PageBean<LeaveRecords> selectByPageAndCondition2(int currentPage, int pageSize, LeaveRecords leaveRecords);


    /**
     * 修改请假申请
     * @param leaveRecords
     */
    void updateLeaveRecords(LeaveRecords leaveRecords);


    /**
     * 根据员工id查询请假申请
     * @param id
     */
    boolean selectLeaveRecords(Integer id);


    /**
     * 删除请假申请
     * @param ids
     */
    void deleteLeaveRecords(int[] ids);

    /**
     * 根据培训活动id，员工id查询培训活动记录
     * @param trainingActivityId
     * @param workerId
     * @return
     */
    boolean selectTrainingActivitiesRecords(Integer trainingActivityId,Integer workerId);

    /**
     * 添加培训活动记录
     * @param trainingActivitiesRecords
     */
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 分页条件查询（培训活动）
     * @param currentPage
     * @param pageSize
     * @param trainingActivities
     * @return
     */
    PageBean<TrainingActivities> selectByPageAndCondition3(int currentPage, int pageSize, TrainingActivities trainingActivities);

    /**
     * 分页条件查询（培训活动记录）
     * @param currentPage
     * @param pageSize
     * @param trainingActivitiesRecords
     * @return
     */
    PageBean<TrainingActivitiesRecords> selectByPageAndCondition4(int currentPage, int pageSize, TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动记录
     * @param ids
     */
    void deleteTrainingActivitiesRecords(int[] ids);

    /**
     * 根据id查询培训活动记录
     * @param id
     * @return
     */
    boolean selectTrainingActivitiesRecords2(Integer id);

    /**
     * 分页条件查询（公告）
     * @param currentPage
     * @param pageSize
     * @param announcements
     * @return
     */
    PageBean<Announcements> selectByPageAndCondition5(int currentPage, int pageSize, Announcements announcements);

    /**
     * 分页条件查询（离职申请）
     * @param currentPage
     * @param pageSize
     * @param resignations
     * @return
     */
    PageBean<Resignations> selectByPageAndCondition6(int currentPage, int pageSize, Resignations resignations);

    /**
     * 修改员工离职申请
     * @param resignations
     */
    void updateResignation(Resignations resignations);

    /**
     * 删除离职申请
     * @param ids
     */
    void deleteResignations(int[] ids);

    /**
     * 根据id查询离职申请
     * @param id
     * @return
     */
    boolean selectResignations(Integer id);

    /**
     * 根据员工id，审批状态查询培训活动记录
     * @param workerId
     * @param approval
     * @return
     */
    Resignations selectResignations2(Integer workerId,Integer approval,Integer status);

    /**
     * 添加离职申请
     * @param resignations
     */
    void addResignations(Resignations resignations);

    /**
     * 分页条件查询（工资记录）
     * @param currentPage
     * @param pageSize
     * @param salaryRecords
     * @return
     */
    PageBean<SalaryRecords> selectByPageAndCondition7(int currentPage, int pageSize, SalaryRecords salaryRecords);

}
