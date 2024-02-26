package com.falling.mapper;

import com.falling.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WorkersMapper {

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    @Select("select * from ademob.workers where name=#{name}")
    @ResultMap("workersResultMap")
    Workers selectWorker(@Param("name") String name);

    /**
     * 修改个人基本信息
     * @param worker
     */
    @Update("update ademob.workers set name=#{name},manager_id=#{managerId} where id=#{id}")
    @ResultMap("workersResultMap")
    void updateBasicInformation(Workers worker);

    /**
     * 修改密码
     * @param worker
     */
    @Update("update ademob.workers set password=#{password},manager_id=#{managerId} where id=#{id}")
    void updatePassword(Workers worker);


    /**
     * 更换头像
     * @param worker
     */
    @Update("update ademob.workers set profile_photo=#{profilePhoto},manager_id=#{managerId} where id=#{id}")
    @ResultMap("workersResultMap")
    void uploadProfilePhoto(Workers worker);


    /**
     * 查询每页显示的出勤记录
     * @param begin
     * @param size
     * @param attendanceRecord
     * @return
     */
    List<AttendanceRecords> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 添加请假申请
     * @param leaveRecords
     */
    @Insert("insert into ademob.leave_records value (null,#{workerId},#{leaveType},#{byear},#{bmonth},#{bday},#{bhour},#{bminute},#{eyear},#{emonth},#{eday},#{ehour},#{eminute},#{fine},#{approval},#{status},#{managerId})")
    void addLeaveRecords(LeaveRecords leaveRecords);

    /**
     * 查询每页显示的请假申请
     * @param begin
     * @param size
     * @param leaveRecords
     * @return
     */
    List<LeaveRecords> selectByPageAndCondition2(@Param("begin") int begin, @Param("size") int size, @Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition2(@Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     * 修改请假申请
     * @param leaveRecords
     */
    @Update("update ademob.leave_records set leave_type=#{leaveType},byear=#{byear},bmonth=#{bmonth},bday=#{bday},bhour=#{bhour},bminute=#{bminute},eyear=#{eyear},emonth=#{emonth},eday=#{eday},ehour=#{ehour},eminute=#{eminute} where id=#{id}")
    @ResultMap("leaveRecordsResultMap")
    void updateLeaveRecords(LeaveRecords leaveRecords);

    /**
     * 根据id查询请假申请
     * @param id
     * @return
     */
    @Select("select * from leave_records where id=#{id}")
    @ResultMap("leaveRecordsResultMap")
    LeaveRecords selectLeaveRecords(@Param("id") Integer id);

    /**
     * 删除请假申请
     * @param ids
     */
    void deleteLeaveRecords(@Param("ids") int[] ids);

    /**
     * 根据员工id，培训活动id查询培训活动记录
     * @param trainingActivityId
     * @param workerId
     * @return
     */
    @Select("select * from training_activities_records where training_activity_id=#{trainingActivityId} and worker_id=#{workerId}")
    @ResultMap("trainingActivitiesRecordsResultMap")
    TrainingActivitiesRecords selectTrainingActivitiesRecords(@Param("trainingActivityId") Integer trainingActivityId,@Param("workerId") Integer workerId);


    /**
     * 添加培训活动记录
     * @param trainingActivitiesRecords
     */
    @Insert("insert into ademob.training_activities_records value (null,#{trainingActivityId},#{workerId},#{score},#{bonus},#{process},#{status},#{managerId})")
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 查询每页显示的培训活动
     * @param begin
     * @param size
     * @param trainingActivities
     * @return
     */
    List<TrainingActivities> selectByPageAndCondition3(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivities") TrainingActivities trainingActivities);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition3( @Param("trainingActivities") TrainingActivities trainingActivities);


    /**
     * 查询每页显示的培训活动记录
     * @param begin
     * @param size
     * @param trainingActivitiesRecords
     * @return
     */
    List<TrainingActivitiesRecords> selectByPageAndCondition4(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition4(@Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动记录
     * @param ids
     */
    void deleteTrainingActivitiesRecords(@Param("ids") int[] ids);


    /**
     * 根据id查询培训活动记录
     * @param id
     * @return
     */
    @Select("select * from training_activities_records where id=#{id}")
    @ResultMap("trainingActivitiesRecordsResultMap")
    TrainingActivitiesRecords selectTrainingActivitiesRecords2(@Param("id") Integer id);

    /**
     * 查询每页显示的公告
     * @param begin
     * @param size
     * @param announcements
     * @return
     */
    List<Announcements> selectByPageAndCondition5(@Param("begin") int begin, @Param("size") int size, @Param("announcements") Announcements announcements);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition5(@Param("announcements") Announcements announcements);

    /**
     * 查询每页显示的离职申请
     * @param begin
     * @param size
     * @param resignations
     * @return
     */
    List<Resignations> selectByPageAndCondition6(@Param("begin") int begin, @Param("size") int size, @Param("resignations") Resignations resignations);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition6( @Param("resignations") Resignations resignations);

    /**
     *  修改离职申请
     * @param resignations
     */

    @Update("update ademob.resignations set reason=#{reason} where id=#{id}")
    @ResultMap("resignationsResultMap")
    void updateResignations(Resignations resignations);

    /**
     * 删除离职申请
     * @param ids
     */
    void deleteResignations(@Param("ids") int[] ids);

    /**
     * 根据id查询离职申请
     * @param id
     * @return
     */
    @Select("select * from resignations where id=#{id}")
    @ResultMap("resignationsResultMap")
    Resignations selectResignations(@Param("id") Integer id);


    /**
     * 根据员工id，审批状态查询培训活动记录
     * @param workerId
     * @param approval
     * @return
     */
    @Select("select * from resignations where worker_id=#{workerId} and approval=#{approval} and status={status}")
    @ResultMap("resignationsResultMap")
    Resignations selectResignations2(@Param("workerId") Integer workerId,@Param("approval") Integer approval,@Param("status") Integer status);


    /**
     * 添加离职申请
     * @param resignations
     */
    @Insert("insert into ademob.resignations value (null,#{workerId},#{reason},#{approval},#{status},#{managerId})")
    void addResignations(Resignations resignations);

    /**
     * 查询每页显示的工资记录
     * @param begin
     * @param size
     * @param salaryRecords
     * @return
     */
    List<SalaryRecords> selectByPageAndCondition7(@Param("begin") int begin, @Param("size") int size, @Param("salaryRecords") SalaryRecords salaryRecords);

    /**
     * 分页条件查询，查询总记录数
     * @return
     */
    int selectTotalCountByCondition7( @Param("salaryRecords") SalaryRecords salaryRecords);
}
