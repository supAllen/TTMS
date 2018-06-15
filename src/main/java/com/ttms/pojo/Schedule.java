package com.ttms.pojo;

import java.util.Date;

public class Schedule {
    private Integer schedId;

    private Integer studioId;

    private Integer playId;

    private Date schedTime;

    private Integer schedTicketPrice;

    public Integer getSchedId() {
        return schedId;
    }

    public void setSchedId(Integer schedId) {
        this.schedId = schedId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getPlayId() {
        return playId;
    }

    public void setPlayId(Integer playId) {
        this.playId = playId;
    }

    public Date getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(Date schedTime) {
        this.schedTime = schedTime;
    }

    public Integer getSchedTicketPrice() {
        return schedTicketPrice;
    }

    public void setSchedTicketPrice(Integer schedTicketPrice) {
        this.schedTicketPrice = schedTicketPrice;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedId=" + schedId +
                ", studioId=" + studioId +
                ", playId=" + playId +
                ", schedTime=" + schedTime +
                ", schedTicketPrice=" + schedTicketPrice +
                '}';
    }
}