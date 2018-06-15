package com.ttms.utils.resultType;

import com.ttms.pojo.Schedule;
import com.ttms.pojo.Studio;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    剧目详情页
 * @Date: Created in 20:15 2018/6/14
 * @Modify By:
 */
public class playInfo {

    private String playName;
    private String playTypeName;
    private String playLangName;
    private Integer playLength;
    private String playIntroduction;
    private String playImage;
    private Integer ticketPrice;
    private List<Schedule> schedule;
    private List<Studio> stuNames;

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Studio> getStuNames() {
        return stuNames;
    }

    public void setStuNames(List<Studio> stuNames) {
        this.stuNames = stuNames;
    }

    public String getPlayImage() {
        return playImage;
    }

    public void setPlayImage(String playImage) {
        this.playImage = playImage;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getPlayLangName() {
        return playLangName;
    }

    public void setPlayLangName(String playLangName) {
        this.playLangName = playLangName;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayTypeName() {
        return playTypeName;
    }

    public void setPlayTypeName(String playTypeName) {
        this.playTypeName = playTypeName;
    }

    public Integer getPlayLength() {
        return playLength;
    }

    public void setPlayLength(Integer playLength) {
        this.playLength = playLength;
    }

    public String getPlayIntroduction() {
        return playIntroduction;
    }

    public void setPlayIntroduction(String playIntroduction) {
        this.playIntroduction = playIntroduction;
    }

    @Override
    public String toString() {
        return "playInfo{" +
                "playName='" + playName + '\'' +
                ", playTypeName='" + playTypeName + '\'' +
                ", playLangName='" + playLangName + '\'' +
                ", playLength=" + playLength +
                ", playIntroduction='" + playIntroduction + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
