package com.falling.service;

import com.falling.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagersService {
    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    Managers selectManager(String name,String password);

    /**
     * 添加员工
     *
     * @param worker
     */
    void addWorker(Workers worker);

    /**
     * 根据用户名查询员工
     *
     * @param name
     * @return
     */
    Workers selectWorker(String name);


    /**
     * 删除（开除）员工
     *
     * @param ids
     */
    void deleteWorkersK(int[] ids, int managerId);

    /**
     * 删除（开除）员工:删除出勤记录
     *
     * @param workersIds
     * @param managerId
     */
    void deleteAttendanceRecordsK(int[] workersIds, int managerId);

    /**
     * 删除（开除）员工：删除请假记录
     *
     * @param workersIds
     * @param managerId
     */
    void deleteLeaveRecordsK(int[] workersIds, int managerId);

    /**
     * 删除（开除）员工:删除离职申请记录
     *
     * @param workersIds
     * @param managerId
     */
    void deleteResignationsK(int[] workersIds, int managerId);

    /**
     * 删除（开除）员工：删除工资记录
     *
     * @param workersIds
     * @param managerId
     */
    void deleteSalaryRecordsK(int[] workersIds, int managerId);

    /**
     * 删除（开除）员工：删除培训活动员工记录
     *
     * @param workersIds
     * @param managerId
     */
    void deleteTrainingActivitiesRecordsK(int[] workersIds, int managerId);

    /**
     * 修改员工信息
     *
     * @param worker
     */
    void updateWorker(Workers worker);

    /**
     * 分页条件查询（员工信息）
     *
     * @param currentPage
     * @param pageSize
     * @param worker
     * @return
     */
    PageBean<Workers> selectByPageAndCondition(int currentPage, int pageSize, Workers worker);

    /**
     * 添加员工出勤记录
     *
     * @param attendanceRecord
     */
    void addAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 删除员工出勤记录
     *
     * @param ids
     */
    void deleteAttendanceRecords(int[] ids, int managerId);

    /**
     * 修改员工出勤记录
     *
     * @param attendanceRecord
     */
    void updateAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 分页条件查询（出勤记录）
     *
     * @param currentPage
     * @param pageSize
     * @param attendanceRecord
     * @return
     */
    PageBean<AttendanceRecords> selectByPageAndCondition2(int currentPage, int pageSize, AttendanceRecords attendanceRecord);

    /**
     * 删除员工请假记录
     *
     * @param ids
     */
    void deleteLeaveRecords(int[] ids, int managerId);

    /**
     * 修改员工请假申请
     *
     * @param leaveRecords
     */
    void updateLeaveRecord(LeaveRecords leaveRecords);

    /**
     * 分页条件查询（请假申请）
     *
     * @param currentPage
     * @param pageSize
     * @param leaveRecords
     * @return
     */
    PageBean<LeaveRecords> selectByPageAndCondition3(int currentPage, int pageSize, LeaveRecords leaveRecords);

    /**
     * 删除员工请假记录
     *
     * @param ids
     */
    void deleteResignations(int[] ids, int managerId);

    /**
     * 修改员工离职申请
     *
     * @param resignations
     */
    void updateResignation(Resignations resignations);

    /**
     * 分页条件查询（离职申请）
     *
     * @param currentPage
     * @param pageSize
     * @param resignations
     * @return
     */
    PageBean<Resignations> selectByPageAndCondition4(int currentPage, int pageSize, Resignations resignations);

    /**
     * 添加培训活动
     *
     * @param trainingActivities
     */
    void addTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 删除培训活动
     *
     * @param ids
     */
    void deleteTrainingActivities(int[] ids, int managerId);

    /**
     * 分页条件查询（培训活动）
     *
     * @param currentPage
     * @param pageSize
     * @param trainingActivities
     * @return
     */
    PageBean<TrainingActivities> selectByPageAndCondition5(int currentPage, int pageSize, TrainingActivities trainingActivities);

    /**
     * 修改培训活动
     *
     * @param trainingActivities
     */
    void updateTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 添加培训活动员工记录
     *
     * @param trainingActivitiesRecords
     */
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动员工记录
     *
     * @param ids
     */
    void deleteTrainingActivitiesRecords(int[] ids, int managerId);

    /**
     * 分页条件查询（培训活动员工记录）
     *
     * @param currentPage
     * @param pageSize
     * @param trainingActivitiesRecords
     * @return
     */
    PageBean<TrainingActivitiesRecords> selectByPageAndCondition6(int currentPage, int pageSize, TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 修改培训活动员工记录
     *
     * @param trainingActivitiesRecords
     */
    void updateTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 添加公告
     *
     * @param announcements
     */
    void addAnnouncements(Announcements announcements);


    /**
     * 删除公告
     *
     * @param ids
     */
    void deleteAnnouncements(int[] ids, int managerId);

    /**
     * 修改公告
     *
     * @param announcements
     */
    void updateAnnouncements(Announcements announcements);

    /**
     * 分页条件查询（公告）
     *
     * @param currentPage
     * @param pageSize
     * @param announcements
     * @return
     */
    PageBean<Announcements> selectByPageAndCondition7(int currentPage, int pageSize, Announcements announcements);


    /**
     * 发工资：查询公司所有在职员工
     *
     * @return
     */
    List<Workers> selectWorkersF();

    /**
     * 发工资：根据员工id查询process=3且status=1的培训活动记录
     *
     * @return
     */
    List<TrainingActivitiesRecords> selectTrainingActivitiesRecordsF(int workerId);

    /**
     * 发工资：修改培训活动记录的process属性
     *
     * @param trainingActivitiesRecords
     */
    void updateTrainingActivitiesRecordsF(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 发工资：根据员工id查询status=1的出勤记录
     *
     * @param workerId
     * @return
     */
    List<AttendanceRecords> selectAttendanceRecordsF(@Param("workerId") int workerId);

    /**
     * 发工资：修改出勤记录的status属性
     *
     * @param attendanceRecords
     */
    void updateAttendanceRecordsF(AttendanceRecords attendanceRecords);

    /**
     * 发工资：根据员工id查询status=1的请假记录
     *
     * @param workerId
     * @return
     */
    List<LeaveRecords> selectLeaveRecordsF(@Param("workerId") int workerId);

    /**
     * 发工资：修改请假记录的status属性
     *
     * @param leaveRecords
     */
    void updateLeaveRecordsF(LeaveRecords leaveRecords);

    /**
     * 发工资：将工资记录存入数据库
     *
     * @param salaryRecords
     */
    void addSalaryRecords(SalaryRecords salaryRecords);

    /**
     * 分页条件查询（工资）
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<SalaryRecords> selectByPageAndCondition8(int currentPage, int pageSize, SalaryRecords salaryRecords);

    /**
     * 修改密码
     * @param managers
     */
    void updatePassword(Managers managers);
}
