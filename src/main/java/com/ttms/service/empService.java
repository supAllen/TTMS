package com.ttms.service;

import com.ttms.dao.EmployeeMapper;
import com.ttms.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 8:44 2018/6/15
 * @Modify By:
 */
@Service
public class empService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public boolean check(String username, String password) throws UnsupportedEncodingException {
        String s = new String(username.getBytes("iso-8859-1"), "UTF-8");
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(username);
        criteria.andEmpPassEqualTo(password);
        boolean check = employeeMapper.selectByExample(employeeExample).size() == 1;
        return check;
    }
}
