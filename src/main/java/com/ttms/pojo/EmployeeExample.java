package com.ttms.pojo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andEmpIdIsNull() {
            addCriterion("emp_id is null");
            return (Criteria) this;
        }

        public Criteria andEmpIdIsNotNull() {
            addCriterion("emp_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmpIdEqualTo(Integer value) {
            addCriterion("emp_id =", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotEqualTo(Integer value) {
            addCriterion("emp_id <>", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThan(Integer value) {
            addCriterion("emp_id >", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("emp_id >=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThan(Integer value) {
            addCriterion("emp_id <", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdLessThanOrEqualTo(Integer value) {
            addCriterion("emp_id <=", value, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdIn(List<Integer> values) {
            addCriterion("emp_id in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotIn(List<Integer> values) {
            addCriterion("emp_id not in", values, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdBetween(Integer value1, Integer value2) {
            addCriterion("emp_id between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("emp_id not between", value1, value2, "empId");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNull() {
            addCriterion("emp_name is null");
            return (Criteria) this;
        }

        public Criteria andEmpNameIsNotNull() {
            addCriterion("emp_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNameEqualTo(String value) {
            addCriterion("emp_name =", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotEqualTo(String value) {
            addCriterion("emp_name <>", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThan(String value) {
            addCriterion("emp_name >", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("emp_name >=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThan(String value) {
            addCriterion("emp_name <", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLessThanOrEqualTo(String value) {
            addCriterion("emp_name <=", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameLike(String value) {
            addCriterion("emp_name like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotLike(String value) {
            addCriterion("emp_name not like", value, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameIn(List<String> values) {
            addCriterion("emp_name in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotIn(List<String> values) {
            addCriterion("emp_name not in", values, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameBetween(String value1, String value2) {
            addCriterion("emp_name between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpNameNotBetween(String value1, String value2) {
            addCriterion("emp_name not between", value1, value2, "empName");
            return (Criteria) this;
        }

        public Criteria andEmpPassIsNull() {
            addCriterion("emp_pass is null");
            return (Criteria) this;
        }

        public Criteria andEmpPassIsNotNull() {
            addCriterion("emp_pass is not null");
            return (Criteria) this;
        }

        public Criteria andEmpPassEqualTo(String value) {
            addCriterion("emp_pass =", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassNotEqualTo(String value) {
            addCriterion("emp_pass <>", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassGreaterThan(String value) {
            addCriterion("emp_pass >", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassGreaterThanOrEqualTo(String value) {
            addCriterion("emp_pass >=", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassLessThan(String value) {
            addCriterion("emp_pass <", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassLessThanOrEqualTo(String value) {
            addCriterion("emp_pass <=", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassLike(String value) {
            addCriterion("emp_pass like", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassNotLike(String value) {
            addCriterion("emp_pass not like", value, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassIn(List<String> values) {
            addCriterion("emp_pass in", values, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassNotIn(List<String> values) {
            addCriterion("emp_pass not in", values, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassBetween(String value1, String value2) {
            addCriterion("emp_pass between", value1, value2, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpPassNotBetween(String value1, String value2) {
            addCriterion("emp_pass not between", value1, value2, "empPass");
            return (Criteria) this;
        }

        public Criteria andEmpTypeIsNull() {
            addCriterion("emp_type is null");
            return (Criteria) this;
        }

        public Criteria andEmpTypeIsNotNull() {
            addCriterion("emp_type is not null");
            return (Criteria) this;
        }

        public Criteria andEmpTypeEqualTo(Byte value) {
            addCriterion("emp_type =", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeNotEqualTo(Byte value) {
            addCriterion("emp_type <>", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeGreaterThan(Byte value) {
            addCriterion("emp_type >", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("emp_type >=", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeLessThan(Byte value) {
            addCriterion("emp_type <", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeLessThanOrEqualTo(Byte value) {
            addCriterion("emp_type <=", value, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeIn(List<Byte> values) {
            addCriterion("emp_type in", values, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeNotIn(List<Byte> values) {
            addCriterion("emp_type not in", values, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeBetween(Byte value1, Byte value2) {
            addCriterion("emp_type between", value1, value2, "empType");
            return (Criteria) this;
        }

        public Criteria andEmpTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("emp_type not between", value1, value2, "empType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}