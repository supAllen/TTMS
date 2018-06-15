package com.ttms.serviceInterface;

import com.ttms.pojo.Seat;
import com.ttms.pojo.Studio;
import com.ttms.pojo.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Allen
 * @Description:    票管理
 * @Date: Created in 16:46 2018/6/4
 * @Modify By:
 */
public interface ticketManager {

    public void addd(Ticket ticket);

    public void del(String ids);

    public void del(Integer schId);

    public void del(List<Integer> schIds);

    public void modifyy(Ticket ticket);

    public List<Ticket> selectAll();

    public Map<Integer,List<Seat>> getSeatAndSch();

    public void batchAddTicket(Integer schId, Integer studioId, Studio studio);

    public boolean ticketOrder(String seatInfo,Integer schId);

    public boolean ticketSales(String ticketIds);

}
