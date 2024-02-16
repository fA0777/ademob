package com.falling.pojo;

import java.time.LocalDateTime;

//请假记录实体类
public class LeaveRecords {
    //id
    private Integer id;
    //员工id
    private Integer workerId;
    //请假类型: 1病假、2事假、3婚假、4丧假、5产假
    private Integer leaveType;
    //请假开始时间
    private LocalDateTime beginTime;
    //请假结束时间
    private LocalDateTime endTime;
    //罚款
    private Integer fine;
    //是否批准: null未审批，0不批准，1批准
    private Integer approval;
    //管理员id
    private Integer managerId;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }
}
