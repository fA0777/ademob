package com.falling.pojo;

import java.time.LocalDateTime;

//员工实体类
public class Workers {
    //id
    private Integer id;
    //用户名
    private String name;
    //密码
    private String password;
    //真实姓名
    private String trueName;
    //上班时间(时)
    private Integer clockInHour;
    //上班时间(分)
    private Integer clockInMinute;
    //下班时间(时)
    private Integer clockOutHour;
    //下班时间(分)
    private Integer clockOutMinute;
    //头像文件路径
    private String profilePhoto;
    //加个底薪属性

    @Override
    public String toString() {
        return "Workers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", clockInHour=" + clockInHour +
                ", clockInMinute=" + clockInMinute +
                ", clockOutHour=" + clockOutHour +
                ", clockOutMinute=" + clockOutMinute +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getClockInHour() {
        return clockInHour;
    }

    public void setClockInHour(Integer clockInHour) {
        this.clockInHour = clockInHour;
    }

    public Integer getClockInMinute() {
        return clockInMinute;
    }

    public void setClockInMinute(Integer clockInMinute) {
        this.clockInMinute = clockInMinute;
    }

    public Integer getClockOutHour() {
        return clockOutHour;
    }

    public void setClockOutHour(Integer clockOutHour) {
        this.clockOutHour = clockOutHour;
    }

    public Integer getClockOutMinute() {
        return clockOutMinute;
    }

    public void setClockOutMinute(Integer clockOutMinute) {
        this.clockOutMinute = clockOutMinute;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
