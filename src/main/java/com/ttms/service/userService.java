package com.ttms.service;

import com.ttms.dao.UserMapper;
import com.ttms.pojo.User;
import com.ttms.pojo.UserExample;
import com.ttms.serviceInterface.commonInterface;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    用户管理
 * @Date: Created in 21:56 2018/6/7
 * @Modify By:
 */
@Service
public class userService implements commonInterface<User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addd(User user) {
        userMapper.insert(user);
    }

    @Override
    public void del(String ids) {
        List<Integer> list = StringSplitUtils.getIds(ids);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdIn(list);
        userMapper.selectByExample(userExample);
    }

    @Override
    public void modifyy(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        userMapper.updateByExample(user,example);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectByExample(null);
    }

    public boolean checkUser(String username, String password){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        criteria.andUserPassEqualTo(password);
        return userMapper.selectByExample(userExample).size() == 1;
    }
}
