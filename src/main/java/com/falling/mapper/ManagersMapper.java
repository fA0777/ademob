package com.falling.mapper;

import com.falling.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ManagersMapper {

    /**
     * 根据用户名和密码查询
     * @param name
     * @param password
     * @return
     */
    @Select("select * from ademob.managers where name=#{name} and password=#{password}")
    Managers selectManager(@Param("name") String name, @Param("password") String password);

    /**
     * 添加员工
     * @param worker
     */
    @Insert("insert into ademob.workers value (null,#{name},#{password},#{trueName},#{clockInHour},#{clockInMinute},#{clockOutHour},#{clockOutMinute},#{profilePhoto})")
    void addWorker(Workers worker);

    /**
     * 删除（开除）员工
     * @param ids
     */
    void deleteWorkers(@Param("ids") int[] ids);

    /**
     * 修改员工信息
     * @param worker
     */
    @Update("update ademob.workers set name=#{name},password=#{password},true_name=#{trueName},clock_in_hour=#{clockInHour},clock_in_minute=#{clockInMinute},clock_out_hour=#{clockOutHour},clock_out_minute=#{clockOutMinute} where id=#{id}")
    @ResultMap("workersResultMap")
    void updateWorker(Workers worker);

    /**
     * 分页条件查询，查询每页显示的员工信息
     * @param begin：开始的索引
     * @param size：每页显示条数
     * @return
     */
    List<Workers> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("worker") Workers worker);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("worker") Workers worker);

    /**
     * 添加员工出勤记录
     * @param attendanceRecord
     */
    @Insert("insert into ademob.attendance_records value (null,#{workerId},#{arriveLate},#{leaveEarly},#{absence},#{fine})")
    void addAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 删除员工出勤记录
     * @param ids
     */
    void deleteAttendanceRecords(@Param("ids") int[] ids);

    /**
     * 修改员工出勤记录
     * @param attendanceRecord
     */
    @Update("update ademob.attendance_records set worker_id=#{workerId},arrive_late=#{arriveLate},leave_early=#{leaveEarly},absence=#{absence},fine=#{fine} where id=#{id}")
    @ResultMap("attendanceRecordsResultMap")
    void updateAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 查询每页显示的出勤记录
     * @param begin
     * @param size
     * @param attendanceRecord
     * @return
     */
    List<AttendanceRecords> selectByPageAndCondition2(@Param("begin") int begin, @Param("size") int size, @Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition2(@Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 修改员工请假申请
     * @param leaveRecords
     */

    @Update("update ademob.leave_records set fine=#{fine},approval=#{approval},manager_id=#{managerId} where id=#{id}")
    @ResultMap("leaveRecordsResultMap")
    void updateLeaveRecord(LeaveRecords leaveRecords);

    /**
     * 查询每页显示的请假申请
     * @param begin
     * @param size
     * @param leaveRecords
     * @return
     */
    List<LeaveRecords> selectByPageAndCondition3(@Param("begin") int begin, @Param("size") int size, @Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition3(@Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     *  修改员工离职申请
     * @param resignations
     */

    @Update("update ademob.resignations set approval=#{approval},manager_id=#{managerId} where id=#{id}")
    @ResultMap("resignationsResultMap")
    void updateResignations(Resignations resignations);

    /**
     * 查询每页显示的离职申请
     * @param begin
     * @param size
     * @param resignations
     * @return
     */
    List<Resignations> selectByPageAndCondition4(@Param("begin") int begin, @Param("size") int size, @Param("resignations") Resignations resignations);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition4( @Param("resignations") Resignations resignations);

    /**
     * 添加培训活动
     * @param trainingActivities
     */
    @Insert("insert into ademob.training_activities value (null,#{trainingContent},#{managerId})")
    void addTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 删除培训活动
     * @param ids
     */
    void deleteTrainingActivities(@Param("ids") int[] ids);

    /**
     * 查询每页显示的培训活动
     * @param begin
     * @param size
     * @param trainingActivities
     * @return
     */
    List<TrainingActivities> selectByPageAndCondition5(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivities") TrainingActivities trainingActivities);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition5( @Param("trainingActivities") TrainingActivities trainingActivities);

    /**
     * 修改培训活动
     * @param trainingActivities
     */
    @Update("update ademob.training_activities set training_content=#{trainingContent},manager_id=#{managerId} where id=#{id}")
    @ResultMap("trainingActivitiesResultMap")
    void updateTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 添加培训活动员工记录
     * @param trainingActivitiesRecords
     */
    @Insert("insert into ademob.training_activities_records value (null,#{trainingActivityId},#{workerId},#{score},#{bonus},#{status},#{managerId})")
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动员工记录
     * @param ids
     */
    void deleteTrainingActivitiesRecords(@Param("ids") int[] ids);

    /**
     * 查询每页显示的培训活动员工记录
     * @param begin
     * @param size
     * @param trainingActivitiesRecords
     * @return
     */
    List<TrainingActivitiesRecords> selectByPageAndCondition6(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition6( @Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 修改培训活动员工记录
     * @param trainingActivitiesRecords
     */
    @Update("update ademob.training_activities_records set training_activity_id=#{trainingActivityId},worker_id=#{workerId},score=#{score},bonus=#{bonus},status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("trainingActivitiesRecordsResultMap")
    void updateTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 添加公告
     * @param announcements
     */
    @Insert("insert into ademob.announcements value (null,#{managerId},#{announcementContent},#{status})")
    void addAnnouncements(Announcements announcements);

    /**
     * 删除公告
     * @param ids
     */
    void deleteAnnouncements(@Param("ids") int[] ids);

    /**
     * 修改公告
     * @param announcements
     */
    @Update("update ademob.announcements set manager_id=#{managerId},announcement_content=#{announcementContent},status=#{status} where id=#{id}")
    @ResultMap("announcementsResultMap")
    void updateAnnouncements(Announcements announcements);

    /**
     * 查询每页显示的公告
     * @param begin
     * @param size
     * @param announcements
     * @return
     */
    List<Announcements> selectByPageAndCondition7(@Param("begin") int begin, @Param("size") int size, @Param("announcements") Announcements announcements);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition7(@Param("announcements") Announcements announcements);

}
