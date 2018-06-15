package com.ttms.serviceInterface;

import com.ttms.pojo.Employee;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    员工管理
 * @Date: Created in 16:47 2018/6/4
 * @Modify By:
 */
public interface employeeManager{
    /**
     * 新任职一名员工
     * @param employee
     */
    void addEmp(Employee employee);

    /**
     * 辞退若干名员工
     * @param empIds
     */
    void dismissEmp(String empIds);
    List<Employee> selectAll();
}
