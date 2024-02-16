package com.falling.pojo;


import java.time.LocalDateTime;

//出勤记录实体类
public class AttendanceRecords {
    //id
    private Integer id;
    //员工id
    private Integer workerId;
    //迟到
    private LocalDateTime arriveLate;
    //早退
    private LocalDateTime leaveEarly;
    //缺勤
    private LocalDateTime absence;
    //罚款
    private Integer fine;

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

    public LocalDateTime getArriveLate() {
        return arriveLate;
    }

    public void setArriveLate(LocalDateTime arriveLate) {
        this.arriveLate = arriveLate;
    }

    public LocalDateTime getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(LocalDateTime leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public LocalDateTime getAbsence() {
        return absence;
    }

    public void setAbsence(LocalDateTime absence) {
        this.absence = absence;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }
}
