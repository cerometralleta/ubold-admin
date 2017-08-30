package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.ComponentType;
import com.ubold.admin.constant.SqlViewConstant;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.repository.impl.JpaRepositoryImpl;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.FormViewService;
import com.ubold.admin.service.SqlDefineService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.utils.SimpleUtils;
import com.ubold.admin.vo.ColumnVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/30.
 */
@Service
public class SqlDefineServiceImpl extends JpaRepositoryImpl<SqlDefineRepository> implements SqlDefineService {

    @Autowired
    FormViewService formViewService;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Response persistent(JSONObject paramJson) {
        SqlDefine sqlDefine = new SqlDefine();
        String sqlId  = paramJson.getString("sqlId");
        String id = paramJson.getString("id");
        if(StringUtils.isBlank(id)){
            sqlDefine.setId(GUID.nextId());
            if(null != this.getRepository().findBySqlId(sqlId)){
                return Response.FAILURE("SQLID重复");
            }
        }else{
            if(CollectionUtils.isNotEmpty(this.getRepository().findBySqlIdAndIdNot(sqlId,id))){
                return Response.FAILURE("SQLID重复");
            }
        }
        sqlDefine.setSqlId(sqlId);
        sqlDefine.setSqlName(paramJson.getString("sqlName"));
        sqlDefine.setMasterTable(paramJson.getString("masterTable"));
        sqlDefine.setMasterTableId(paramJson.getString("masterTableId"));
        sqlDefine.setSelectSql(paramJson.getString("selectSql"));
        this.getRepository().save(sqlDefine);
        return Response.SUCCESS();
    }

    @Override
    public List<ColumnVo> getColumnsBySqlId(String sqlId) {
        List<ColumnVo> list = new ArrayList<ColumnVo>();
        SqlDefine sqlDefine = this.getRepository().findBySqlId(sqlId);

        //获取主表实际列用来过滤
        String masterSql = "SELECT * FROM " + sqlDefine.getMasterTable() + " T WHERE 1=2 " ;
        SqlRowSet resultSet = namedParameterJdbcTemplate.queryForRowSet(masterSql,new HashMap<String,String>());
        SqlRowSetMetaData srsmd = resultSet.getMetaData();
        Map<String,Object> masterFieldMap = new HashMap<>();
        for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
            masterFieldMap.put(srsmd.getColumnLabel(i), srsmd.getColumnLabel(i));
        }

        //获取查询所有列
        String viewSql = " SELECT T.* FROM (" + sqlDefine.getSelectSql()+") T WHERE 1=2 ";

        //通过临时表 找到对应的字段属性
        resultSet = namedParameterJdbcTemplate.queryForRowSet(viewSql,new HashMap<String,String>());
        srsmd = resultSet.getMetaData();
        ColumnVo field = null;
        for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
            field = new ColumnVo();
            this.setFieldTitle(field, srsmd.getColumnLabel(i));
            field.setField(srsmd.getColumnLabel(i));// as 后的值 ，getColumnName 原始值
            field.setMaxlength(srsmd.getPrecision(i));
            field.setDataType(srsmd.getColumnTypeName(i));
            field.setFieldType(ComponentType.TEXT.getValue());
            //判断是否是日期类型
            if (SimpleUtils.getDataType(field.getDataType()).equals(SqlViewConstant.COLUMNTYPE_DATE)) {
                field.setFieldType(ComponentType.DATEPICKER.getValue());
            }
            field.setIdx(i-1);
            if(masterFieldMap.containsKey(field.getField())){

                //修改类型
                field.setUpdateType(SqlViewConstant.MODIFTY_ENABLE);

                //是否增加
                field.setInsert(true);
                field.setVisible(true);
                field.setCardVisible(true);
                field.setSwitchable(true);
            }
            list.add(field);
        }
        return list;
    }

    /**
     * 设置名称
     * @param sqlViewField
     * @param field
     */
    private void setFieldTitle(ColumnVo sqlViewField,String field){
        switch (field.toUpperCase()) {
            case SqlViewConstant.LAST_UPDATE_TIME:
                sqlViewField.setTitle("更新时间");
                break;
            case SqlViewConstant.LAST_UPDATE_USER:
                sqlViewField.setTitle("更新者");
                break;
            case SqlViewConstant.CREATE_TIME:
                sqlViewField.setTitle("创建时间");
                break;
            case SqlViewConstant.CREATE_USER:
                sqlViewField.setTitle("创建者");
                break;
            case SqlViewConstant.VERSION:
                sqlViewField.setTitle("版本号");
                break;
            default:
                sqlViewField.setTitle(field);
                break;
        }
    }

    private List<ColumnVo> getColumnsByTableName(String tableName){



        return null;
    }

}
