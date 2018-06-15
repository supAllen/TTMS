package com.ttms.service;

import com.ttms.dao.DataDictMapper;
import com.ttms.dao.PlayMapper;
import com.ttms.dao.ScheduleMapper;
import com.ttms.dao.StudioMapper;
import com.ttms.jedis.JedisClient;
import com.ttms.pojo.*;
import com.ttms.serviceInterface.playManager;
import com.ttms.utils.JsonUtils;
import com.ttms.utils.resultType.playInfo;
import com.ttms.utils.resultType.playPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Allen
 * @Description: 剧目管理
 * @Date: Created in 8:29 2018/6/5
 * @Modify By:
 */
@Service
public class playService implements playManager {

    @Autowired
    private PlayMapper playMapper;
    @Autowired
    private DataDictMapper ddm;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${PLAY_MAP}")
    private String PLAY_MAP;
    @Value("${PAGE_SIZE}")
    private Integer PAGE_SIZE;
    @Value("${PLAY_PRE}")
    private String PLAY_PRE;

    /**
     * 添加剧目
     *
     * @param play
     */
    @Override
    public void addplay(Play play) {
        playMapper.insert(play);
        // 更新缓存
        // 更新粒度更细
        try {
            int i = playMapper.countByExample(null);
            int i2 = i % PAGE_SIZE;
            int i1 = i / PAGE_SIZE;
            if (i2 != 0) {
                ++i1;
            }
            // 更新缓存可以按页更新
            jedisClient.hdel(PLAY_MAP, i1 + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Play> selectAll() {
        throw new IllegalArgumentException();
    }

    /**
     * 根据剧目语言value或者剧目类型value得到ID
     *
     * @param play_language
     * @return
     */
    private Integer selectPlayLanguageOrTypeId(String play_language) {
        DataDictExample dde = new DataDictExample();
        DataDictExample.Criteria criteria = dde.createCriteria();
        criteria.andDictValueEqualTo(play_language);
        List<DataDict> dataDicts = ddm.selectByExample(dde);
        Integer dictId = dataDicts.get(0).getDictId();
        return dictId;
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Play> selectAll(int pageIndex) {
        // 获取总的电影数
        int playCount = playMapper.countByExample(null);
        // 查看缓存是否命中
        try {
            String data = jedisClient.hget(PLAY_MAP, "" + pageIndex);
            if (null != data) {
                return JsonUtils.jsonToList(data, Play.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 没有命中
        if (pageIndex < 1)
            pageIndex = 1;
        if (playCount <= PAGE_SIZE)
            PAGE_SIZE = playCount - 1;
        System.out.println(pageIndex);
        System.out.println(PAGE_SIZE);
        playPage playPage = new playPage();
        playPage.setCount(PAGE_SIZE);
        playPage.setStart(pageIndex - 1);
        List<Play> playListIntoRedis = playMapper.getPrePageData(playPage);

        try {
            String json = JsonUtils.objectToJson(playListIntoRedis);
            jedisClient.hset(PLAY_MAP, "" + pageIndex, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playListIntoRedis;
    }

    /**
     * 根据电影语言和电影类别查询
     * 需要的格式是：两个需要根据,隔开
     * 四种
     * 1 什么都没选
     * 2 选了剧目类型
     * 3 选了剧目语言
     * 4 两个都选了
     *
     * @param condition
     */
    @Override
    public List<Play> selectByCondition(String condition) {
        // 1 什么都没选
        if (condition == null) {
            return selectAll(1);
        }
        String[] conditions = condition.split(",");
        // 2 选了剧目类型
        if ("#".equals(conditions[1])) {
            Integer play_type = getPlay_typeID(conditions[0]);
            return selectByType(play_type);
        }
        // 3 选了剧目语言
        if ("#".equals(conditions[0])) {
            Integer play_language = getPlay_typeID(conditions[1]);
            return selectByLanguage(play_language);
        }
        // 4 两个都选了
        Integer play_type = getPlay_typeID(conditions[0]);
        Integer play_language = getPlay_typeID(conditions[1]);
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pec = pe.createCriteria();
        pec.andPlayTypeIdEqualTo(play_type);
        pec.andPlayLangIdEqualTo(play_language);
        return playMapper.selectByExample(pe);
    }

    /**
     * 获取ID
     *
     * @param con
     * @return
     */
    private Integer getPlay_typeID(String con) {
        DataDictExample dde = new DataDictExample();
        DataDictExample.Criteria ddc = dde.createCriteria();
        ddc.andDictValueEqualTo(con);
        List<DataDict> dataDicts = ddm.selectByExample(dde);
        return dataDicts.get(0).getDictId();
    }

    /**
     * 根据剧目语言查询
     *
     * @param play_language
     * @return
     */
    private List<Play> selectByLanguage(Integer play_language) {
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pc = pe.createCriteria();
        pc.andPlayLangIdEqualTo(play_language);
        List<Play> playList = playMapper.selectByExample(pe);
        return playList;
    }

    /**
     * 根据剧目类型查询
     *
     * @param play_type
     * @return
     */
    private List<Play> selectByType(Integer play_type) {
        PlayExample pe = new PlayExample();
        PlayExample.Criteria pc = pe.createCriteria();
        pc.andPlayTypeIdEqualTo(play_type);
        List<Play> playList = playMapper.selectByExample(pe);
        return playList;
    }

    /**
     * 电影下架
     *
     * @param playId
     */
    @Override
    public void dropOff(Integer playId) {
        // 修改状态
        Play play = playMapper.selectByPrimaryKey(playId);
        play.setPlayStatus((byte) -1);
        // 进行更新
        PlayExample example = new PlayExample();
        PlayExample.Criteria criteria = example.createCriteria();
        criteria.andPlayIdEqualTo(playId);
        playMapper.updateByExample(play, example);
        // 更新时需要删除缓存,只需要覆盖部分，即只覆盖修改的部分
        try {
            jedisClient.hdel(PLAY_PRE, "" + playId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计票房
     *
     * @param playId
     * @return
     */
    @Override
    public Integer boxOfficeStatistics(Integer playId) {
        Play play = playMapper.selectByPrimaryKey(playId);
        return play.getPlayBookingOffice();
    }

    /**
     * 获取所有的电影类型和所有电影语言和电影状态选择
     *
     * @return
     */
    @Override
    public List<List<DataDict>> getPlayTypesAndLanguages() {
        // 查询电影类别
        DataDictExample example = new DataDictExample();
        DataDictExample.Criteria criteria = example.createCriteria();
        criteria.andDictParentIdEqualTo(2);
        List<DataDict> types = ddm.selectByExample(example);

        // 查询电影语言
        DataDictExample example2 = new DataDictExample();
        DataDictExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andDictParentIdEqualTo(3);
        List<DataDict> languages = ddm.selectByExample(example2);

        List<List<DataDict>> choiceInfo = new ArrayList<>();
        choiceInfo.add(types);
        choiceInfo.add(languages);
        return choiceInfo;
    }

    public Play getPlayInfo(Integer pid) {
        try {
            String hget = jedisClient.hget(PLAY_PRE, pid + "");
            if (hget != null)
                return JsonUtils.jsonToPojo(hget, Play.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Play play = playMapper.selectByPrimaryKey(pid);
        try {
            // 添加缓存
            String s = JsonUtils.objectToJson(play);
            jedisClient.hset(PLAY_PRE, "" + pid, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return play;
    }

    /**
     * 查询每个电影所对应的电影前端需要展示的信息
     *
     * @param pid
     * @return
     */
    public playInfo getPrePlayInfo(Integer pid) {
        Play play = playMapper.selectByPrimaryKey(pid);
        // 所对应的演出计划可能是空的
        List<Studio> studios = null;
        // 获取当天的演出计划
        List<Schedule> schedules = scheduleMapper.selectSchByPlayId(pid);
        System.out.println(schedules);
        if (!schedules.isEmpty()) {
            List<Integer> stuIds = new ArrayList<>();
            for (int i = 0; i < schedules.size(); i++) {
                stuIds.add(schedules.get(i).getStudioId());
            }
            StudioExample studioExample = new StudioExample();
            StudioExample.Criteria studioExampleCriteria = studioExample.createCriteria();
            studioExampleCriteria.andStudioIdIn(stuIds);
            studios = studioMapper.selectByExample(studioExample);
        }
        // 获取剧目的其他信息
        playInfo playInfo = new playInfo();
        playInfo.setStuNames(studios);
        playInfo.setSchedule(schedules);
        playInfo.setPlayIntroduction(play.getPlayIntroduction());
        playInfo.setPlayLength(play.getPlayLength());
        playInfo.setPlayName(play.getPlayName());
        String typeValue = getLangOrType(play.getPlayTypeId());
        String langValue = getLangOrType(play.getPlayLangId());
        playInfo.setPlayTypeName(typeValue);
        playInfo.setPlayLangName(langValue);
        playInfo.setPlayImage(play.getPlayImage());
        playInfo.setTicketPrice(play.getPlayTicketPrice());
        return playInfo;
    }

    private String getLangOrType(Integer id) {
        DataDictExample dataDictExample = new DataDictExample();
        DataDictExample.Criteria criteria = dataDictExample.createCriteria();
        criteria.andDictIdEqualTo(id);
        List<DataDict> dataDicts = ddm.selectByExample(dataDictExample);
        return dataDicts.get(0).getDictValue();
    }

}
