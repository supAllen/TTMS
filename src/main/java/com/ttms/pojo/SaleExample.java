package com.ttms.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SaleExample() {
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

        public Criteria andSaleIdIsNull() {
            addCriterion("sale_ID is null");
            return (Criteria) this;
        }

        public Criteria andSaleIdIsNotNull() {
            addCriterion("sale_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSaleIdEqualTo(Long value) {
            addCriterion("sale_ID =", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotEqualTo(Long value) {
            addCriterion("sale_ID <>", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdGreaterThan(Long value) {
            addCriterion("sale_ID >", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_ID >=", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdLessThan(Long value) {
            addCriterion("sale_ID <", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdLessThanOrEqualTo(Long value) {
            addCriterion("sale_ID <=", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdIn(List<Long> values) {
            addCriterion("sale_ID in", values, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotIn(List<Long> values) {
            addCriterion("sale_ID not in", values, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdBetween(Long value1, Long value2) {
            addCriterion("sale_ID between", value1, value2, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotBetween(Long value1, Long value2) {
            addCriterion("sale_ID not between", value1, value2, "saleId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIsNull() {
            addCriterion("sale_time is null");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIsNotNull() {
            addCriterion("sale_time is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTimeEqualTo(Date value) {
            addCriterion("sale_time =", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotEqualTo(Date value) {
            addCriterion("sale_time <>", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeGreaterThan(Date value) {
            addCriterion("sale_time >", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sale_time >=", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeLessThan(Date value) {
            addCriterion("sale_time <", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeLessThanOrEqualTo(Date value) {
            addCriterion("sale_time <=", value, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeIn(List<Date> values) {
            addCriterion("sale_time in", values, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotIn(List<Date> values) {
            addCriterion("sale_time not in", values, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeBetween(Date value1, Date value2) {
            addCriterion("sale_time between", value1, value2, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSaleTimeNotBetween(Date value1, Date value2) {
            addCriterion("sale_time not between", value1, value2, "saleTime");
            return (Criteria) this;
        }

        public Criteria andSalePaymentIsNull() {
            addCriterion("sale_payment is null");
            return (Criteria) this;
        }

        public Criteria andSalePaymentIsNotNull() {
            addCriterion("sale_payment is not null");
            return (Criteria) this;
        }

        public Criteria andSalePaymentEqualTo(Integer value) {
            addCriterion("sale_payment =", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentNotEqualTo(Integer value) {
            addCriterion("sale_payment <>", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentGreaterThan(Integer value) {
            addCriterion("sale_payment >", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_payment >=", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentLessThan(Integer value) {
            addCriterion("sale_payment <", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentLessThanOrEqualTo(Integer value) {
            addCriterion("sale_payment <=", value, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentIn(List<Integer> values) {
            addCriterion("sale_payment in", values, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentNotIn(List<Integer> values) {
            addCriterion("sale_payment not in", values, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentBetween(Integer value1, Integer value2) {
            addCriterion("sale_payment between", value1, value2, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSalePaymentNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_payment not between", value1, value2, "salePayment");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNull() {
            addCriterion("sale_type is null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNotNull() {
            addCriterion("sale_type is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeEqualTo(Byte value) {
            addCriterion("sale_type =", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotEqualTo(Byte value) {
            addCriterion("sale_type <>", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThan(Byte value) {
            addCriterion("sale_type >", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("sale_type >=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThan(Byte value) {
            addCriterion("sale_type <", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThanOrEqualTo(Byte value) {
            addCriterion("sale_type <=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIn(List<Byte> values) {
            addCriterion("sale_type in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotIn(List<Byte> values) {
            addCriterion("sale_type not in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeBetween(Byte value1, Byte value2) {
            addCriterion("sale_type between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("sale_type not between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNull() {
            addCriterion("sale_status is null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNotNull() {
            addCriterion("sale_status is not null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusEqualTo(Byte value) {
            addCriterion("sale_status =", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotEqualTo(Byte value) {
            addCriterion("sale_status <>", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThan(Byte value) {
            addCriterion("sale_status >", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("sale_status >=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThan(Byte value) {
            addCriterion("sale_status <", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThanOrEqualTo(Byte value) {
            addCriterion("sale_status <=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIn(List<Byte> values) {
            addCriterion("sale_status in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotIn(List<Byte> values) {
            addCriterion("sale_status not in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusBetween(Byte value1, Byte value2) {
            addCriterion("sale_status between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("sale_status not between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleContentIsNull() {
            addCriterion("sale_content is null");
            return (Criteria) this;
        }

        public Criteria andSaleContentIsNotNull() {
            addCriterion("sale_content is not null");
            return (Criteria) this;
        }

        public Criteria andSaleContentEqualTo(String value) {
            addCriterion("sale_content =", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentNotEqualTo(String value) {
            addCriterion("sale_content <>", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentGreaterThan(String value) {
            addCriterion("sale_content >", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentGreaterThanOrEqualTo(String value) {
            addCriterion("sale_content >=", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentLessThan(String value) {
            addCriterion("sale_content <", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentLessThanOrEqualTo(String value) {
            addCriterion("sale_content <=", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentLike(String value) {
            addCriterion("sale_content like", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentNotLike(String value) {
            addCriterion("sale_content not like", value, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentIn(List<String> values) {
            addCriterion("sale_content in", values, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentNotIn(List<String> values) {
            addCriterion("sale_content not in", values, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentBetween(String value1, String value2) {
            addCriterion("sale_content between", value1, value2, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSaleContentNotBetween(String value1, String value2) {
            addCriterion("sale_content not between", value1, value2, "saleContent");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameIsNull() {
            addCriterion("sale_playName is null");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameIsNotNull() {
            addCriterion("sale_playName is not null");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameEqualTo(String value) {
            addCriterion("sale_playName =", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameNotEqualTo(String value) {
            addCriterion("sale_playName <>", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameGreaterThan(String value) {
            addCriterion("sale_playName >", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameGreaterThanOrEqualTo(String value) {
            addCriterion("sale_playName >=", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameLessThan(String value) {
            addCriterion("sale_playName <", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameLessThanOrEqualTo(String value) {
            addCriterion("sale_playName <=", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameLike(String value) {
            addCriterion("sale_playName like", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameNotLike(String value) {
            addCriterion("sale_playName not like", value, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameIn(List<String> values) {
            addCriterion("sale_playName in", values, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameNotIn(List<String> values) {
            addCriterion("sale_playName not in", values, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameBetween(String value1, String value2) {
            addCriterion("sale_playName between", value1, value2, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andSalePlaynameNotBetween(String value1, String value2) {
            addCriterion("sale_playName not between", value1, value2, "salePlayname");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeIsNull() {
            addCriterion("play_startTime is null");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeIsNotNull() {
            addCriterion("play_startTime is not null");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeEqualTo(Date value) {
            addCriterion("play_startTime =", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeNotEqualTo(Date value) {
            addCriterion("play_startTime <>", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeGreaterThan(Date value) {
            addCriterion("play_startTime >", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("play_startTime >=", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeLessThan(Date value) {
            addCriterion("play_startTime <", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("play_startTime <=", value, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeIn(List<Date> values) {
            addCriterion("play_startTime in", values, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeNotIn(List<Date> values) {
            addCriterion("play_startTime not in", values, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeBetween(Date value1, Date value2) {
            addCriterion("play_startTime between", value1, value2, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("play_startTime not between", value1, value2, "playStarttime");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameIsNull() {
            addCriterion("play_studioName is null");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameIsNotNull() {
            addCriterion("play_studioName is not null");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameEqualTo(String value) {
            addCriterion("play_studioName =", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameNotEqualTo(String value) {
            addCriterion("play_studioName <>", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameGreaterThan(String value) {
            addCriterion("play_studioName >", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameGreaterThanOrEqualTo(String value) {
            addCriterion("play_studioName >=", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameLessThan(String value) {
            addCriterion("play_studioName <", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameLessThanOrEqualTo(String value) {
            addCriterion("play_studioName <=", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameLike(String value) {
            addCriterion("play_studioName like", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameNotLike(String value) {
            addCriterion("play_studioName not like", value, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameIn(List<String> values) {
            addCriterion("play_studioName in", values, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameNotIn(List<String> values) {
            addCriterion("play_studioName not in", values, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameBetween(String value1, String value2) {
            addCriterion("play_studioName between", value1, value2, "playStudioname");
            return (Criteria) this;
        }

        public Criteria andPlayStudionameNotBetween(String value1, String value2) {
            addCriterion("play_studioName not between", value1, value2, "playStudioname");
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