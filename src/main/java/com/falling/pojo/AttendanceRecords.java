package com.falling.pojo;


//出勤记录实体类
public class AttendanceRecords {
    //id
    private Integer id;
    //员工id
    private Integer workerId;
    //类型 1迟到 2早退 3缺勤
    private Integer type;
    //日期
    private String date;
    //时间
    private String time;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
