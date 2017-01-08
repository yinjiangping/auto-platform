package team.yqby.platform.dto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowOrderExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("ORDER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("ORDER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("ORDER_TIME =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("ORDER_TIME <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("ORDER_TIME >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ORDER_TIME >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("ORDER_TIME <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("ORDER_TIME <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("ORDER_TIME in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("ORDER_TIME not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("ORDER_TIME between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("ORDER_TIME not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andFlowIdIsNull() {
            addCriterion("FLOW_ID is null");
            return (Criteria) this;
        }

        public Criteria andFlowIdIsNotNull() {
            addCriterion("FLOW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFlowIdEqualTo(Long value) {
            addCriterion("FLOW_ID =", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotEqualTo(Long value) {
            addCriterion("FLOW_ID <>", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThan(Long value) {
            addCriterion("FLOW_ID >", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FLOW_ID >=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThan(Long value) {
            addCriterion("FLOW_ID <", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThanOrEqualTo(Long value) {
            addCriterion("FLOW_ID <=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdIn(List<Long> values) {
            addCriterion("FLOW_ID in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotIn(List<Long> values) {
            addCriterion("FLOW_ID not in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdBetween(Long value1, Long value2) {
            addCriterion("FLOW_ID between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotBetween(Long value1, Long value2) {
            addCriterion("FLOW_ID not between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdIsNull() {
            addCriterion("OUTTER_FLOW_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdIsNotNull() {
            addCriterion("OUTTER_FLOW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdEqualTo(String value) {
            addCriterion("OUTTER_FLOW_ID =", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdNotEqualTo(String value) {
            addCriterion("OUTTER_FLOW_ID <>", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdGreaterThan(String value) {
            addCriterion("OUTTER_FLOW_ID >", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUTTER_FLOW_ID >=", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdLessThan(String value) {
            addCriterion("OUTTER_FLOW_ID <", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdLessThanOrEqualTo(String value) {
            addCriterion("OUTTER_FLOW_ID <=", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdLike(String value) {
            addCriterion("OUTTER_FLOW_ID like", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdNotLike(String value) {
            addCriterion("OUTTER_FLOW_ID not like", value, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdIn(List<String> values) {
            addCriterion("OUTTER_FLOW_ID in", values, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdNotIn(List<String> values) {
            addCriterion("OUTTER_FLOW_ID not in", values, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdBetween(String value1, String value2) {
            addCriterion("OUTTER_FLOW_ID between", value1, value2, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOutterFlowIdNotBetween(String value1, String value2) {
            addCriterion("OUTTER_FLOW_ID not between", value1, value2, "outterFlowId");
            return (Criteria) this;
        }

        public Criteria andOriginalCostIsNull() {
            addCriterion("ORIGINAL_COST is null");
            return (Criteria) this;
        }

        public Criteria andOriginalCostIsNotNull() {
            addCriterion("ORIGINAL_COST is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalCostEqualTo(Long value) {
            addCriterion("ORIGINAL_COST =", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostNotEqualTo(Long value) {
            addCriterion("ORIGINAL_COST <>", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostGreaterThan(Long value) {
            addCriterion("ORIGINAL_COST >", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostGreaterThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_COST >=", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostLessThan(Long value) {
            addCriterion("ORIGINAL_COST <", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostLessThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_COST <=", value, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostIn(List<Long> values) {
            addCriterion("ORIGINAL_COST in", values, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostNotIn(List<Long> values) {
            addCriterion("ORIGINAL_COST not in", values, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_COST between", value1, value2, "originalCost");
            return (Criteria) this;
        }

        public Criteria andOriginalCostNotBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_COST not between", value1, value2, "originalCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostIsNull() {
            addCriterion("CURRENT_COST is null");
            return (Criteria) this;
        }

        public Criteria andCurrentCostIsNotNull() {
            addCriterion("CURRENT_COST is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentCostEqualTo(Long value) {
            addCriterion("CURRENT_COST =", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostNotEqualTo(Long value) {
            addCriterion("CURRENT_COST <>", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostGreaterThan(Long value) {
            addCriterion("CURRENT_COST >", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostGreaterThanOrEqualTo(Long value) {
            addCriterion("CURRENT_COST >=", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostLessThan(Long value) {
            addCriterion("CURRENT_COST <", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostLessThanOrEqualTo(Long value) {
            addCriterion("CURRENT_COST <=", value, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostIn(List<Long> values) {
            addCriterion("CURRENT_COST in", values, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostNotIn(List<Long> values) {
            addCriterion("CURRENT_COST not in", values, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostBetween(Long value1, Long value2) {
            addCriterion("CURRENT_COST between", value1, value2, "currentCost");
            return (Criteria) this;
        }

        public Criteria andCurrentCostNotBetween(Long value1, Long value2) {
            addCriterion("CURRENT_COST not between", value1, value2, "currentCost");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("OPEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("OPEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("OPEN_ID =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("OPEN_ID <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("OPEN_ID >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_ID >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("OPEN_ID <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("OPEN_ID <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("OPEN_ID like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("OPEN_ID not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("OPEN_ID in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("OPEN_ID not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("OPEN_ID between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("OPEN_ID not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andTransStatusIsNull() {
            addCriterion("TRANS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTransStatusIsNotNull() {
            addCriterion("TRANS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTransStatusEqualTo(String value) {
            addCriterion("TRANS_STATUS =", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotEqualTo(String value) {
            addCriterion("TRANS_STATUS <>", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusGreaterThan(String value) {
            addCriterion("TRANS_STATUS >", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_STATUS >=", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLessThan(String value) {
            addCriterion("TRANS_STATUS <", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLessThanOrEqualTo(String value) {
            addCriterion("TRANS_STATUS <=", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusLike(String value) {
            addCriterion("TRANS_STATUS like", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotLike(String value) {
            addCriterion("TRANS_STATUS not like", value, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusIn(List<String> values) {
            addCriterion("TRANS_STATUS in", values, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotIn(List<String> values) {
            addCriterion("TRANS_STATUS not in", values, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusBetween(String value1, String value2) {
            addCriterion("TRANS_STATUS between", value1, value2, "transStatus");
            return (Criteria) this;
        }

        public Criteria andTransStatusNotBetween(String value1, String value2) {
            addCriterion("TRANS_STATUS not between", value1, value2, "transStatus");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeIsNull() {
            addCriterion("PAY_REQ_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeIsNotNull() {
            addCriterion("PAY_REQ_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeEqualTo(Date value) {
            addCriterion("PAY_REQ_TIME =", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeNotEqualTo(Date value) {
            addCriterion("PAY_REQ_TIME <>", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeGreaterThan(Date value) {
            addCriterion("PAY_REQ_TIME >", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAY_REQ_TIME >=", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeLessThan(Date value) {
            addCriterion("PAY_REQ_TIME <", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAY_REQ_TIME <=", value, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeIn(List<Date> values) {
            addCriterion("PAY_REQ_TIME in", values, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeNotIn(List<Date> values) {
            addCriterion("PAY_REQ_TIME not in", values, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeBetween(Date value1, Date value2) {
            addCriterion("PAY_REQ_TIME between", value1, value2, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayReqTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAY_REQ_TIME not between", value1, value2, "payReqTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeIsNull() {
            addCriterion("PAY_RESP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeIsNotNull() {
            addCriterion("PAY_RESP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeEqualTo(Date value) {
            addCriterion("PAY_RESP_TIME =", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeNotEqualTo(Date value) {
            addCriterion("PAY_RESP_TIME <>", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeGreaterThan(Date value) {
            addCriterion("PAY_RESP_TIME >", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAY_RESP_TIME >=", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeLessThan(Date value) {
            addCriterion("PAY_RESP_TIME <", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAY_RESP_TIME <=", value, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeIn(List<Date> values) {
            addCriterion("PAY_RESP_TIME in", values, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeNotIn(List<Date> values) {
            addCriterion("PAY_RESP_TIME not in", values, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeBetween(Date value1, Date value2) {
            addCriterion("PAY_RESP_TIME between", value1, value2, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAY_RESP_TIME not between", value1, value2, "payRespTime");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeIsNull() {
            addCriterion("PAY_RESP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeIsNotNull() {
            addCriterion("PAY_RESP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeEqualTo(String value) {
            addCriterion("PAY_RESP_CODE =", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeNotEqualTo(String value) {
            addCriterion("PAY_RESP_CODE <>", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeGreaterThan(String value) {
            addCriterion("PAY_RESP_CODE >", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_RESP_CODE >=", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeLessThan(String value) {
            addCriterion("PAY_RESP_CODE <", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeLessThanOrEqualTo(String value) {
            addCriterion("PAY_RESP_CODE <=", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeLike(String value) {
            addCriterion("PAY_RESP_CODE like", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeNotLike(String value) {
            addCriterion("PAY_RESP_CODE not like", value, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeIn(List<String> values) {
            addCriterion("PAY_RESP_CODE in", values, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeNotIn(List<String> values) {
            addCriterion("PAY_RESP_CODE not in", values, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeBetween(String value1, String value2) {
            addCriterion("PAY_RESP_CODE between", value1, value2, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespCodeNotBetween(String value1, String value2) {
            addCriterion("PAY_RESP_CODE not between", value1, value2, "payRespCode");
            return (Criteria) this;
        }

        public Criteria andPayRespDescIsNull() {
            addCriterion("PAY_RESP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPayRespDescIsNotNull() {
            addCriterion("PAY_RESP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPayRespDescEqualTo(String value) {
            addCriterion("PAY_RESP_DESC =", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescNotEqualTo(String value) {
            addCriterion("PAY_RESP_DESC <>", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescGreaterThan(String value) {
            addCriterion("PAY_RESP_DESC >", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_RESP_DESC >=", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescLessThan(String value) {
            addCriterion("PAY_RESP_DESC <", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescLessThanOrEqualTo(String value) {
            addCriterion("PAY_RESP_DESC <=", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescLike(String value) {
            addCriterion("PAY_RESP_DESC like", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescNotLike(String value) {
            addCriterion("PAY_RESP_DESC not like", value, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescIn(List<String> values) {
            addCriterion("PAY_RESP_DESC in", values, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescNotIn(List<String> values) {
            addCriterion("PAY_RESP_DESC not in", values, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescBetween(String value1, String value2) {
            addCriterion("PAY_RESP_DESC between", value1, value2, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andPayRespDescNotBetween(String value1, String value2) {
            addCriterion("PAY_RESP_DESC not between", value1, value2, "payRespDesc");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(String value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(String value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(String value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(String value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLike(String value) {
            addCriterion("CHECK_STATUS like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotLike(String value) {
            addCriterion("CHECK_STATUS not like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<String> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<String> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagIsNull() {
            addCriterion("ARCHIVE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagIsNotNull() {
            addCriterion("ARCHIVE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagEqualTo(String value) {
            addCriterion("ARCHIVE_FLAG =", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagNotEqualTo(String value) {
            addCriterion("ARCHIVE_FLAG <>", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagGreaterThan(String value) {
            addCriterion("ARCHIVE_FLAG >", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLAG >=", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagLessThan(String value) {
            addCriterion("ARCHIVE_FLAG <", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLAG <=", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagLike(String value) {
            addCriterion("ARCHIVE_FLAG like", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagNotLike(String value) {
            addCriterion("ARCHIVE_FLAG not like", value, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagIn(List<String> values) {
            addCriterion("ARCHIVE_FLAG in", values, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagNotIn(List<String> values) {
            addCriterion("ARCHIVE_FLAG not in", values, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLAG between", value1, value2, "archiveFlag");
            return (Criteria) this;
        }

        public Criteria andArchiveFlagNotBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLAG not between", value1, value2, "archiveFlag");
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