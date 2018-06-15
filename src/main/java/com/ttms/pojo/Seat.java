package com.ttms.pojo;

public class Seat {
    private Integer seatId;

    private Integer studioId;

    private Integer seatRow;

    private Integer seatColumn;

    private Byte seatStatus;

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(Integer seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Byte getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Byte seatStatus) {
        this.seatStatus = seatStatus;
    }
}