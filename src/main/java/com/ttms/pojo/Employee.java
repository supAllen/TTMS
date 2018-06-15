package com.ttms.pojo;

public class Employee {
    private Integer empId;

    private String empName;

    private String empPass;

    private Byte empType;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass == null ? null : empPass.trim();
    }

    public Byte getEmpType() {
        return empType;
    }

    public void setEmpType(Byte empType) {
        this.empType = empType;
    }
}