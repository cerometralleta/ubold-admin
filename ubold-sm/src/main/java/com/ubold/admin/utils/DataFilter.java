package com.ubold.admin.utils;

import com.ubold.admin.constant.SqlExpression;
import com.ubold.admin.vo.ConditionParam;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL过滤	
 * @author zkning
 */
public class DataFilter {
	private static String WHERE =" WHERE ";
	private String querySql = null;
	private StringBuffer condition = new StringBuffer();
	private StringBuffer orderBy = new StringBuffer();
	private Map<String,Object> params = new HashMap<>();

	//兼容bootstraptable排序
	private String sortOrder;
	private String sortName;

    public DataFilter(List<ConditionParam> conditionVoList) {
        addCondition(conditionVoList);
    }

    public DataFilter() {
    }

    public static DataFilter getInstance() {
        return new DataFilter();
    }

    public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public void addCondition(List<ConditionParam> ConditionParamList){
		if(ConditionParamList == null)
			return;

		for(ConditionParam cond : ConditionParamList){

			//排序字段
			addCondition(cond.getField(),cond.getExpression(),cond.getValue(),cond.getSortOrder());
		}
	}

	public void addCondition(ConditionParam cond){
		addCondition(cond.getField(),cond.getExpression(),cond.getValue(),cond.getSortOrder());
    }

	public void EQ(String alias,String value){
		addCondition(alias, SqlExpression.EQ,value,null);
	}

	public void addCondition(String alias,String expr ,String value,String sort){
		if(StringUtils.isNotBlank(value)){
			if(StringUtils.isNotBlank(condition)){
				condition.append(" AND ");
			}
			condition.append(" t.").append(alias);

			//拼装条件表达式
            if (SqlExpression.IN.equals(expr) ||
                    SqlExpression.NOT_IN.equals(expr)){
				condition.append(" " + expr +" (").append(":").append(alias).append(") ");
            } else if (SqlExpression.NULL.equals(expr) ||
                    SqlExpression.NOT_NULL.equals(expr)){
				condition.append(expr);
			}else{
				condition.append(" " + expr +" ").append(":").append(alias);
			}

			//输入值
			if(SqlExpression.LIKE.equals(expr)){
				params.put(alias, "%"+value+"%");
            } else if (SqlExpression.NULL.equals(expr) ||
                    SqlExpression.NOT_NULL.equals(expr)){
            } else if (SqlExpression.IN.equals(expr) ||
                    SqlExpression.NOT_IN.equals(expr)){
				params.put(alias, Lists.newArrayList(value.split(",")));
			}else{
				params.put(alias, value);
			}
		}
		addSort(alias, sort);
	}

	/**
	 * 增加排序
	 * @param alias
	 * @param sort
	 */
	public void addSort(String alias,String sort){
		if(StringUtils.isBlank(sort) || StringUtils.isBlank(alias)){
			return;
		}
		if(StringUtils.isBlank(orderBy)){
			orderBy.append(" ORDER BY ");
		}else{
			orderBy.append(" ,");
		}
		orderBy.append(alias).append(" ").append(sort);
	}

	/**
	 * count sql
	 * @return
	 */
	public String countSql(){
		StringBuffer storeSql = new StringBuffer();
		storeSql.append("select count(1) from (")
		.append(this.querySql)
		.append(") t ");
		if(StringUtils.isNotBlank(condition)){
            storeSql.append(WHERE).append(condition);
        }
		return storeSql.toString();
	}

	/**
	 * SQL
	 * @return
	 */
	public String getSql(){
		StringBuffer storeSql = new StringBuffer();
		storeSql.append("select t.* from (")
		.append(querySql)
		.append(") t ");
		if(StringUtils.isNotBlank(condition)){
            storeSql.append(WHERE).append(condition);
        }
		this.addSort(this.sortName,this.sortOrder);
		return storeSql.append(orderBy).toString();
	}

	/**
	 * 分页SQL
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public String createPager(Integer pageNo,Integer pageSize){
        if (null == pageNo) {
            pageNo = 1;
        }
        if (null == pageSize) {
            pageSize = 50;
        }
        StringBuffer storeSql = new StringBuffer();
		storeSql.append("select t.* from (")
		.append(querySql)
		.append(") t ");
		if(StringUtils.isNotBlank(condition)){
            storeSql.append(WHERE).append(condition);
        }
		this.addSort(this.sortName,this.sortOrder);
		storeSql.append(orderBy);
		if(null != pageNo && null != pageSize){
			storeSql.append(" LIMIT ")
			.append((pageNo - 1) * pageSize)
			.append(",")
			.append(pageSize);
		}
		return storeSql.toString();
	}

	/**
	 * condition sql
	 * @return
	 */
	public String conditionSql(){
		return condition.append(orderBy).toString();
	}

	/**
	 * 获取参数
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 添加参数
	 * @param alis
	 * @param value
	 */
	public void put(String alis,Object value){
		params.put(alis, value);
	}

    public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

}
