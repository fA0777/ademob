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
    //审批结果：0未审批，1审批通过培训中（未设定奖金），2审批不通过，3审批通过且培训完成（设定了奖金，未发放），4奖金已发放到当月工资中
    private Integer process;
    //状态：1启用，2软删除
    private Integer status;
    //管理员id
    private Integer managerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
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
