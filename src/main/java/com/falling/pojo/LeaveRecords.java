package com.falling.pojo;

//请假记录实体类
public class LeaveRecords {
    //id
    private Integer id;
    //员工id
    private Integer workerId;
    //请假类型: 1病假、2事假、3婚假、4丧假、5产假
    private Integer leaveType;
    //请假开始时间
    //日期
    private String bdate;
    //时间
    private String btime;
    //请假结束时间
    //日期
    private String edate;
    //时间
    private String etime;
    //罚款
    private Integer fine;
    //是否批准: 0未审批，1批准，2不批准
    private Integer approval;
    //状态:1启用 2软删除
    private Integer status;
    //管理员id
    private Integer managerId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
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
