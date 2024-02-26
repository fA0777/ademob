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
    //时间:年
    private Integer byear;
    //时间：月
    private Integer bmonth;
    //时间：日
    private Integer bday;
    //时间：时
    private Integer bhour;
    //时间：分
    private Integer bminute;
    //请假结束时间
    //时间:年
    private Integer eyear;
    //时间：月
    private Integer emonth;
    //时间：日
    private Integer eday;
    //时间：时
    private Integer ehour;
    //时间：分
    private Integer eminute;
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

    public Integer getByear() {
        return byear;
    }

    public void setByear(Integer byear) {
        this.byear = byear;
    }

    public Integer getBmonth() {
        return bmonth;
    }

    public void setBmonth(Integer bmonth) {
        this.bmonth = bmonth;
    }

    public Integer getBday() {
        return bday;
    }

    public void setBday(Integer bday) {
        this.bday = bday;
    }

    public Integer getBhour() {
        return bhour;
    }

    public void setBhour(Integer bhour) {
        this.bhour = bhour;
    }

    public Integer getBminute() {
        return bminute;
    }

    public void setBminute(Integer bminute) {
        this.bminute = bminute;
    }

    public Integer getEyear() {
        return eyear;
    }

    public void setEyear(Integer eyear) {
        this.eyear = eyear;
    }

    public Integer getEmonth() {
        return emonth;
    }

    public void setEmonth(Integer emonth) {
        this.emonth = emonth;
    }

    public Integer getEday() {
        return eday;
    }

    public void setEday(Integer eday) {
        this.eday = eday;
    }

    public Integer getEhour() {
        return ehour;
    }

    public void setEhour(Integer ehour) {
        this.ehour = ehour;
    }

    public Integer getEminute() {
        return eminute;
    }

    public void setEminute(Integer eminute) {
        this.eminute = eminute;
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
