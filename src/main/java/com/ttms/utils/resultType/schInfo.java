package com.ttms.utils.resultType;

import com.ttms.pojo.Play;
import com.ttms.pojo.Studio;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    演出计划需要传递的东西
 * @Date: Created in 16:39 2018/6/13
 * @Modify By:
 */
public class schInfo {

    private List<Play> playList;
    private List<Studio> studioList;

    public List<Play> getPlayList() {
        return playList;
    }

    public void setPlayList(List<Play> playList) {
        this.playList = playList;
    }

    public List<Studio> getStudioList() {
        return studioList;
    }

    public void setStudioList(List<Studio> studioList) {
        this.studioList = studioList;
    }
}
