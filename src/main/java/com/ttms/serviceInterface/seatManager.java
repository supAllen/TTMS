package com.ttms.serviceInterface;

import com.ttms.pojo.Seat;
import com.ttms.pojo.Studio;

import java.util.List;

/**
 * @Author: Allen
 * @Description:   座位管理
 * @Date: Created in 16:36 2018/6/4
 * @Modify By:
 */
public interface seatManager {

    public void addd(Seat seat);

    public void del(String ids);

    public void del(List<Integer> studioIds);

    public void modifySeatStatus(String seatIds);

    public void modifyy(Seat seat);

    public List<Seat> selectAll();

    public List<Seat> selectBySchIdAllSeat(Integer schId);

    public void batchAdd(Studio studio);
}
