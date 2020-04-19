package com.basic.zjgfbcc.pojo;

import io.swagger.models.auth.In;

/**
 * @author wzl
 */
public class ProposingPojo {

    /**
     * 完成项目进度
     */
    Integer projectComplete = 0;

    /**
     * 超出
     */
    Integer outOfSchedule = 0;

    /**
     * 落实
     */
    Integer implementationSchedule = 0;

    /**
     * 滞后
     */
    Integer behindSchedule = 0;

    /**
     * 未启动
     */
    Integer notStart = 0;

    /**
     * 已交办数量
     */
    Integer assignSummary = 0;

    public Integer getProjectComplete() {
        return projectComplete;
    }

    public void setProjectComplete(Integer projectComplete) {
        this.projectComplete = projectComplete;
    }

    public Integer getOutOfSchedule() {
        return outOfSchedule;
    }

    public void setOutOfSchedule(Integer outOfSchedule) {
        this.outOfSchedule = outOfSchedule;
    }

    public Integer getImplementationSchedule() {
        return implementationSchedule;
    }

    public void setImplementationSchedule(Integer implementationSchedule) {
        this.implementationSchedule = implementationSchedule;
    }

    public Integer getBehindSchedule() {
        return behindSchedule;
    }

    public void setBehindSchedule(Integer behindSchedule) {
        this.behindSchedule = behindSchedule;
    }

    public Integer getNotStart() {
        return notStart;
    }

    public void setNotStart(Integer notStart) {
        this.notStart = notStart;
    }

    public Integer getAssignSummary() {
        return assignSummary;
    }

    public void setAssignSummary(Integer assignSummary) {
        this.assignSummary = assignSummary;
    }
}
