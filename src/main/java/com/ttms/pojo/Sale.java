package com.ttms.pojo;

import java.util.Date;

public class Sale {
    private Long saleId;

    private Integer userId;

    private Date saleTime;

    private Integer salePayment;

    private Byte saleType;

    private Byte saleStatus;

    private String saleContent;

    private String salePlayname;

    private Date playStarttime;

    private String playStudioname;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getSalePayment() {
        return salePayment;
    }

    public void setSalePayment(Integer salePayment) {
        this.salePayment = salePayment;
    }

    public Byte getSaleType() {
        return saleType;
    }

    public void setSaleType(Byte saleType) {
        this.saleType = saleType;
    }

    public Byte getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getSaleContent() {
        return saleContent;
    }

    public void setSaleContent(String saleContent) {
        this.saleContent = saleContent == null ? null : saleContent.trim();
    }

    public String getSalePlayname() {
        return salePlayname;
    }

    public void setSalePlayname(String salePlayname) {
        this.salePlayname = salePlayname == null ? null : salePlayname.trim();
    }

    public Date getPlayStarttime() {
        return playStarttime;
    }

    public void setPlayStarttime(Date playStarttime) {
        this.playStarttime = playStarttime;
    }

    public String getPlayStudioname() {
        return playStudioname;
    }

    public void setPlayStudioname(String playStudioname) {
        this.playStudioname = playStudioname == null ? null : playStudioname.trim();
    }
}