package com.falling.mapper;

import com.falling.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ManagersMapper {

    /**
     * 根据用户名和密码查询
     * @param name
     * @return
     */
    @Select("select * from ademob.managers where name=#{name}")
    Managers selectManager(String name);

    /**
     * 添加员工
     *
     * @param worker
     */
    @Insert("insert into ademob.workers value (null,#{name},#{salt},#{password},#{trueName},#{clockIn},#{clockOut},#{profilePhoto},#{basicSalary},#{status},#{managerId})")
    void addWorker(Workers worker);

    /**
     * 根据用户名查询员工
     *
     * @param name
     * @return
     */
    @Select("select * from ademob.workers where name=#{name}")
    Workers selectWorker(@Param("name") String name);

    /**
     * 删除（开除）员工
     *
     * @param ids
     */
    void deleteWorkersK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 删除（开除）员工:删除出勤记录
     *
     * @param ids
     */
    void deleteAttendanceRecordsK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 删除（开除）员工：删除请假记录
     *
     * @param ids
     */
    void deleteLeaveRecordsK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 删除（开除）员工:删除离职申请记录
     *
     * @param ids
     */
    void deleteResignationsK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 删除（开除）员工：删除工资记录
     *
     * @param ids
     */
    void deleteSalaryRecordsK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 删除（开除）员工：删除培训活动员工记录
     *
     * @param ids
     */
    void deleteTrainingActivitiesRecordsK(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 修改员工信息
     *
     * @param worker
     */
    @Update("update ademob.workers set name=#{name},password=#{password},true_name=#{trueName},clock_in=#{clockIn},clock_out=#{clockOut},basic_salary=#{basicSalary},manager_id=#{managerId} where id=#{id}")
    @ResultMap("workersResultMap")
    void updateWorker(Workers worker);

    /**
     * 分页条件查询，查询每页显示的员工信息
     *
     * @param begin：开始的索引
     * @param size：每页显示条数
     * @return
     */
    List<Workers> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("worker") Workers worker);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition(@Param("worker") Workers worker);

    /**
     * 添加员工出勤记录
     *
     * @param attendanceRecord
     */
    @Insert("insert into ademob.attendance_records value (null,#{workerId},#{type},#{date},#{time},#{fine},#{status},#{managerId})")
    void addAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 删除员工出勤记录
     *
     * @param ids
     */

    void deleteAttendanceRecords(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 修改员工出勤记录
     *
     * @param attendanceRecord
     */
    @Update("update ademob.attendance_records set worker_id=#{workerId},type=#{type},date=#{date},time=#{time},fine=#{fine},status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("attendanceRecordsResultMap")
    void updateAttendanceRecord(AttendanceRecords attendanceRecord);

    /**
     * 查询每页显示的出勤记录
     *
     * @param begin
     * @param size
     * @param attendanceRecord
     * @return
     */
    List<AttendanceRecords> selectByPageAndCondition2(@Param("begin") int begin, @Param("size") int size, @Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition2(@Param("attendanceRecord") AttendanceRecords attendanceRecord);

    /**
     * 删除员工请假记录
     *
     * @param ids
     */

    void deleteLeaveRecords(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 修改员工请假申请
     *
     * @param leaveRecords
     */

    @Update("update ademob.leave_records set fine=#{fine},approval=#{approval},manager_id=#{managerId} where id=#{id}")
    @ResultMap("leaveRecordsResultMap")
    void updateLeaveRecord(LeaveRecords leaveRecords);

    /**
     * 查询每页显示的请假申请
     *
     * @param begin
     * @param size
     * @param leaveRecords
     * @return
     */
    List<LeaveRecords> selectByPageAndCondition3(@Param("begin") int begin, @Param("size") int size, @Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition3(@Param("leaveRecords") LeaveRecords leaveRecords);

    /**
     * 删除员工离职申请
     *
     * @param ids
     */

    void deleteResignations(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 修改员工离职申请
     *
     * @param resignations
     */

    @Update("update ademob.resignations set approval=#{approval},manager_id=#{managerId} where id=#{id}")
    @ResultMap("resignationsResultMap")
    void updateResignations(Resignations resignations);

    /**
     * 查询每页显示的离职申请
     *
     * @param begin
     * @param size
     * @param resignations
     * @return
     */
    List<Resignations> selectByPageAndCondition4(@Param("begin") int begin, @Param("size") int size, @Param("resignations") Resignations resignations);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition4(@Param("resignations") Resignations resignations);

    /**
     * 添加培训活动
     *
     * @param trainingActivities
     */
    @Insert("insert into ademob.training_activities value (null,#{trainingContent},#{status},#{managerId})")
    void addTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 删除培训活动
     *
     * @param ids
     */
    @ResultMap("trainingActivitiesResultMap")
    void deleteTrainingActivities(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 查询每页显示的培训活动
     *
     * @param begin
     * @param size
     * @param trainingActivities
     * @return
     */
    List<TrainingActivities> selectByPageAndCondition5(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivities") TrainingActivities trainingActivities);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition5(@Param("trainingActivities") TrainingActivities trainingActivities);

    /**
     * 修改培训活动
     *
     * @param trainingActivities
     */
    @Update("update ademob.training_activities set training_content=#{trainingContent},manager_id=#{managerId} where id=#{id}")
    @ResultMap("trainingActivitiesResultMap")
    void updateTrainingActivities(TrainingActivities trainingActivities);

    /**
     * 添加培训活动员工记录
     *
     * @param trainingActivitiesRecords
     */
    @Insert("insert into ademob.training_activities_records value (null,#{trainingActivityId},#{workerId},#{score},#{bonus},#{process},#{status},#{managerId})")
    void addTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 删除培训活动员工记录
     *
     * @param ids
     */
    void deleteTrainingActivitiesRecords(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 查询每页显示的培训活动员工记录
     *
     * @param begin
     * @param size
     * @param trainingActivitiesRecords
     * @return
     */
    List<TrainingActivitiesRecords> selectByPageAndCondition6(@Param("begin") int begin, @Param("size") int size, @Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition6(@Param("trainingActivitiesRecords") TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 修改培训活动员工记录
     *
     * @param trainingActivitiesRecords
     */
    @Update("update ademob.training_activities_records set training_activity_id=#{trainingActivityId},worker_id=#{workerId},score=#{score},bonus=#{bonus},process=#{process},status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("trainingActivitiesRecordsResultMap")
    void updateTrainingActivitiesRecords(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 添加公告
     *
     * @param announcements
     */
    @Insert("insert into ademob.announcements value (null,#{managerId},#{announcementContent},#{status})")
    void addAnnouncements(Announcements announcements);

    /**
     * 删除公告
     *
     * @param ids
     */
    void deleteAnnouncements(@Param("ids") int[] ids, @Param("managerId") int managerId);

    /**
     * 修改公告
     *
     * @param announcements
     */
    @Update("update ademob.announcements set manager_id=#{managerId},announcement_content=#{announcementContent},status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("announcementsResultMap")
    void updateAnnouncements(Announcements announcements);

    /**
     * 查询每页显示的公告
     *
     * @param begin
     * @param size
     * @param announcements
     * @return
     */
    List<Announcements> selectByPageAndCondition7(@Param("begin") int begin, @Param("size") int size, @Param("announcements") Announcements announcements);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition7(@Param("announcements") Announcements announcements);

    /**
     * 发工资：查询公司所有在职员工
     *
     * @return
     */
    @Select("select * from ademob.workers where status=1")
    @ResultMap("workersResultMap")
    List<Workers> selectWorkersF();

    /**
     * 发工资：根据员工id查询process=3且status=1的培训活动记录
     *
     * @param id
     * @return
     */
    @Select("select * from ademob.training_activities_records where worker_id=#{workerId} and process=3 and status=1")
    List<TrainingActivitiesRecords> selectTrainingActivitiesRecordsF(@Param("workerId") int id);

    /**
     * 发工资：修改培训活动记录的process属性
     *
     * @param trainingActivitiesRecords
     */
    @Update("update ademob.training_activities_records set process=#{process},manager_id=#{managerId} where id=#{id}")
    @ResultMap("trainingActivitiesRecordsResultMap")
    void updateTrainingActivitiesRecordsF(TrainingActivitiesRecords trainingActivitiesRecords);

    /**
     * 发工资：根据员工id查询status=1的出勤记录
     *
     * @param workerId
     * @return
     */
    @Select("select * from ademob.attendance_records where worker_id=#{workerId} and status=1")
    List<AttendanceRecords> selectAttendanceRecordsF(@Param("workerId") int workerId);

    /**
     * 发工资：修改出勤记录的status属性
     *
     * @param attendanceRecords
     */
    @Update("update ademob.attendance_records set status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("attendanceRecordsResultMap")
    void updateAttendanceRecordsF(AttendanceRecords attendanceRecords);

    /**
     * 发工资：根据员工id查询status=1的请假记录
     *
     * @param workerId
     * @return
     */
    @Select("select * from ademob.leave_records where worker_id=#{workerId} and status=1")
    List<LeaveRecords> selectLeaveRecordsF(@Param("workerId") int workerId);

    /**
     * 发工资：修改请假记录的status属性
     *
     * @param leaveRecords
     */
    @Update("update ademob.leave_records set status=#{status},manager_id=#{managerId} where id=#{id}")
    @ResultMap("leaveRecordsResultMap")
    void updateLeaveRecordsF(LeaveRecords leaveRecords);

    /**
     * 发工资：将工资记录存入数据库
     *
     * @param salaryRecords
     */
    @Insert("insert into ademob.salary_records value (null,#{workerId},#{year},#{month},#{basicSalary},#{bonus},#{fine},#{totalSalary},#{status},#{managerId})")
    void addSalaryRecords(SalaryRecords salaryRecords);

    /**
     * 查询每页显示的工资记录
     *
     * @param begin
     * @param size
     * @param salaryRecords
     * @return
     */
    List<SalaryRecords> selectByPageAndCondition8(@Param("begin") int begin, @Param("size") int size, @Param("salaryRecords") SalaryRecords salaryRecords);

    /**
     * 分页条件查询，查询总记录数
     *
     * @return
     */
    int selectTotalCountByCondition8(@Param("salaryRecords") SalaryRecords salaryRecords);

    /**
     * 修改密码
     * @param managers
     */
    @Update("update ademob.managers set password=#{password} where id=#{id}")
    void updatePassword(Managers managers);

    /**
     * 首次登录后，更新管理员信息
     * @param managers
     */
    @Update("update ademob.managers set salt=#{salt},password=#{password} where id=#{id}")
    void updateLogin(Managers managers);
}
