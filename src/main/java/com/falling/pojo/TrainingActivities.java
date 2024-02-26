package com.falling.pojo;
//培训活动实体类
public class TrainingActivities {
    //id
    private Integer id;
    //培训活动内容
    private String trainingContent;
    //状态：1启用，2软删除
    private Integer status;
    //管理员id
    private Integer managerId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}
