package team.yqby.platform.dto.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowStockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowStockExample() {
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

        public Criteria andFlowIdIsNull() {
            addCriterion("FLOW_ID is null");
            return (Criteria) this;
        }

        public Criteria andFlowIdIsNotNull() {
            addCriterion("FLOW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFlowIdEqualTo(String value) {
            addCriterion("FLOW_ID =", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotEqualTo(String value) {
            addCriterion("FLOW_ID <>", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThan(String value) {
            addCriterion("FLOW_ID >", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThanOrEqualTo(String value) {
            addCriterion("FLOW_ID >=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThan(String value) {
            addCriterion("FLOW_ID <", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThanOrEqualTo(String value) {
            addCriterion("FLOW_ID <=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLike(String value) {
            addCriterion("FLOW_ID like", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotLike(String value) {
            addCriterion("FLOW_ID not like", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdIn(List<String> values) {
            addCriterion("FLOW_ID in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotIn(List<String> values) {
            addCriterion("FLOW_ID not in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdBetween(String value1, String value2) {
            addCriterion("FLOW_ID between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotBetween(String value1, String value2) {
            addCriterion("FLOW_ID not between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowBelongIsNull() {
            addCriterion("FLOW_BELONG is null");
            return (Criteria) this;
        }

        public Criteria andFlowBelongIsNotNull() {
            addCriterion("FLOW_BELONG is not null");
            return (Criteria) this;
        }

        public Criteria andFlowBelongEqualTo(String value) {
            addCriterion("FLOW_BELONG =", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongNotEqualTo(String value) {
            addCriterion("FLOW_BELONG <>", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongGreaterThan(String value) {
            addCriterion("FLOW_BELONG >", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongGreaterThanOrEqualTo(String value) {
            addCriterion("FLOW_BELONG >=", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongLessThan(String value) {
            addCriterion("FLOW_BELONG <", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongLessThanOrEqualTo(String value) {
            addCriterion("FLOW_BELONG <=", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongLike(String value) {
            addCriterion("FLOW_BELONG like", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongNotLike(String value) {
            addCriterion("FLOW_BELONG not like", value, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongIn(List<String> values) {
            addCriterion("FLOW_BELONG in", values, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongNotIn(List<String> values) {
            addCriterion("FLOW_BELONG not in", values, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongBetween(String value1, String value2) {
            addCriterion("FLOW_BELONG between", value1, value2, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowBelongNotBetween(String value1, String value2) {
            addCriterion("FLOW_BELONG not between", value1, value2, "flowBelong");
            return (Criteria) this;
        }

        public Criteria andFlowTitleIsNull() {
            addCriterion("FLOW_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andFlowTitleIsNotNull() {
            addCriterion("FLOW_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTitleEqualTo(String value) {
            addCriterion("FLOW_TITLE =", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleNotEqualTo(String value) {
            addCriterion("FLOW_TITLE <>", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleGreaterThan(String value) {
            addCriterion("FLOW_TITLE >", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleGreaterThanOrEqualTo(String value) {
            addCriterion("FLOW_TITLE >=", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleLessThan(String value) {
            addCriterion("FLOW_TITLE <", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleLessThanOrEqualTo(String value) {
            addCriterion("FLOW_TITLE <=", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleLike(String value) {
            addCriterion("FLOW_TITLE like", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleNotLike(String value) {
            addCriterion("FLOW_TITLE not like", value, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleIn(List<String> values) {
            addCriterion("FLOW_TITLE in", values, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleNotIn(List<String> values) {
            addCriterion("FLOW_TITLE not in", values, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleBetween(String value1, String value2) {
            addCriterion("FLOW_TITLE between", value1, value2, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowTitleNotBetween(String value1, String value2) {
            addCriterion("FLOW_TITLE not between", value1, value2, "flowTitle");
            return (Criteria) this;
        }

        public Criteria andFlowDescIsNull() {
            addCriterion("FLOW_DESC is null");
            return (Criteria) this;
        }

        public Criteria andFlowDescIsNotNull() {
            addCriterion("FLOW_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andFlowDescEqualTo(String value) {
            addCriterion("FLOW_DESC =", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescNotEqualTo(String value) {
            addCriterion("FLOW_DESC <>", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescGreaterThan(String value) {
            addCriterion("FLOW_DESC >", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescGreaterThanOrEqualTo(String value) {
            addCriterion("FLOW_DESC >=", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescLessThan(String value) {
            addCriterion("FLOW_DESC <", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescLessThanOrEqualTo(String value) {
            addCriterion("FLOW_DESC <=", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescLike(String value) {
            addCriterion("FLOW_DESC like", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescNotLike(String value) {
            addCriterion("FLOW_DESC not like", value, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescIn(List<String> values) {
            addCriterion("FLOW_DESC in", values, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescNotIn(List<String> values) {
            addCriterion("FLOW_DESC not in", values, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescBetween(String value1, String value2) {
            addCriterion("FLOW_DESC between", value1, value2, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowDescNotBetween(String value1, String value2) {
            addCriterion("FLOW_DESC not between", value1, value2, "flowDesc");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostIsNull() {
            addCriterion("FLOW_ORIGINAL_COST is null");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostIsNotNull() {
            addCriterion("FLOW_ORIGINAL_COST is not null");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostEqualTo(Long value) {
            addCriterion("FLOW_ORIGINAL_COST =", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostNotEqualTo(Long value) {
            addCriterion("FLOW_ORIGINAL_COST <>", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostGreaterThan(Long value) {
            addCriterion("FLOW_ORIGINAL_COST >", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostGreaterThanOrEqualTo(Long value) {
            addCriterion("FLOW_ORIGINAL_COST >=", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostLessThan(Long value) {
            addCriterion("FLOW_ORIGINAL_COST <", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostLessThanOrEqualTo(Long value) {
            addCriterion("FLOW_ORIGINAL_COST <=", value, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostIn(List<Long> values) {
            addCriterion("FLOW_ORIGINAL_COST in", values, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostNotIn(List<Long> values) {
            addCriterion("FLOW_ORIGINAL_COST not in", values, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostBetween(Long value1, Long value2) {
            addCriterion("FLOW_ORIGINAL_COST between", value1, value2, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowOriginalCostNotBetween(Long value1, Long value2) {
            addCriterion("FLOW_ORIGINAL_COST not between", value1, value2, "flowOriginalCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostIsNull() {
            addCriterion("FLOW_CURRENT_COST is null");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostIsNotNull() {
            addCriterion("FLOW_CURRENT_COST is not null");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostEqualTo(Long value) {
            addCriterion("FLOW_CURRENT_COST =", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostNotEqualTo(Long value) {
            addCriterion("FLOW_CURRENT_COST <>", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostGreaterThan(Long value) {
            addCriterion("FLOW_CURRENT_COST >", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostGreaterThanOrEqualTo(Long value) {
            addCriterion("FLOW_CURRENT_COST >=", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostLessThan(Long value) {
            addCriterion("FLOW_CURRENT_COST <", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostLessThanOrEqualTo(Long value) {
            addCriterion("FLOW_CURRENT_COST <=", value, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostIn(List<Long> values) {
            addCriterion("FLOW_CURRENT_COST in", values, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostNotIn(List<Long> values) {
            addCriterion("FLOW_CURRENT_COST not in", values, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostBetween(Long value1, Long value2) {
            addCriterion("FLOW_CURRENT_COST between", value1, value2, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andFlowCurrentCostNotBetween(Long value1, Long value2) {
            addCriterion("FLOW_CURRENT_COST not between", value1, value2, "flowCurrentCost");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("STOCK is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Long value) {
            addCriterion("STOCK =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Long value) {
            addCriterion("STOCK <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Long value) {
            addCriterion("STOCK >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Long value) {
            addCriterion("STOCK >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Long value) {
            addCriterion("STOCK <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Long value) {
            addCriterion("STOCK <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Long> values) {
            addCriterion("STOCK in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Long> values) {
            addCriterion("STOCK not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Long value1, Long value2) {
            addCriterion("STOCK between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Long value1, Long value2) {
            addCriterion("STOCK not between", value1, value2, "stock");
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