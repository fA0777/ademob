package com.falling.pojo;


import java.time.LocalDate;
import java.time.LocalDateTime;

//出勤记录实体类
public class AttendanceRecords {
    //id
    private Integer id;
    //员工id
    private Integer workerId;
    //类型 1迟到 2早退 3缺勤
    private Integer type;
    //时间:年
    private Integer year;
    //时间：月
    private Integer month;
    //时间：日
    private Integer day;
    //时间：时
    private Integer hour;
    //时间：分
    private Integer minute;
    //罚款
    private Integer fine;
    //状态 1刚添加，未结算到工资记录中 2结算到本月的工资中了,将其软删除
    private Integer status;
    //管理员id
    private Integer managerId;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

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
}
