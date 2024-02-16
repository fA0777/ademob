package com.falling.pojo;
//培训活动记录实体类
public class TrainingActivitiesRecords {
    //id
    private Integer id;
    //培训活动id
    private Integer trainingActivityId;
    //员工id
    private Integer workerId;
    //得分
    private Double score;
    //奖金
    private Double bonus;
    //状态：初始值为0，培训完成为1，软删除为2
    private Integer status;
    //管理员id
    private Integer managerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainingActivityId() {
        return trainingActivityId;
    }

    public void setTrainingActivityId(Integer trainingActivityId) {
        this.trainingActivityId = trainingActivityId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
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
