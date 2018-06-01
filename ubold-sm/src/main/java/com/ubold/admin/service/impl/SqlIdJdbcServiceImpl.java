package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.*;
import com.ubold.admin.domain.DataView;
import com.ubold.admin.domain.SqlDefine;
import com.ubold.admin.exception.ServiceException;
import com.ubold.admin.factory.JdbcTemplateFactory;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.request.ZtreeParamsRequest;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.DataViewService;
import com.ubold.admin.service.SqlIdJdbcService;
import com.ubold.admin.util.GUID;
import com.ubold.admin.utils.DataFilter;
import com.ubold.admin.utils.SimpleUtils;
import com.ubold.admin.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * @author zkning
 */
@Slf4j
@Service
public class SqlIdJdbcServiceImpl implements SqlIdJdbcService {

    @Autowired
    DataViewService dataViewService;

    @Autowired
    SqlDefineRepository sqlDefineRepository;

    @Autowired
    JdbcTemplateFactory jdbcTemplateFactory;


    //默认空字符串
    private static final String BLANK_STR =  "#000000";

    @Override
    public List<ColumnParam> getColumnsBySqlId(String sqlId) {
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(sqlId);

        //获取主表实际列用来过滤
        String resultSql=new StringBuffer("select * from ").append(sqlDefine.getMasterTable()).append(" t where 1=2 ").toString();
        SqlRowSet resultSet = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                .queryForRowSet(resultSql,new HashMap<>());
        SqlRowSetMetaData srsmd = resultSet.getMetaData();
        Map<String, Object> masterFieldMap = new HashMap<>();
        for (int i = 1; i < srsmd.getColumnCount() + 1; i++) {
            masterFieldMap.put(srsmd.getColumnLabel(i).toUpperCase(), srsmd.getColumnLabel(i));
        }

        //查询SQL列信息列表
        return createColumnParamList(sqlDefine,masterFieldMap);
    }

    protected List<ColumnParam> createColumnParamList(SqlDefine sqlDefine,Map<String, Object> masterFieldMap){
        Connection connection = null;
        try {
            List<ColumnParam> list = new ArrayList<ColumnParam>();
            
            //获取查询所有列
            String querySql = new StringBuffer(" select t.* from (")
                    .append(sqlDefine.getSelectSql())
                    .append(") t where 1=2 ").toString();

            //通过临时表 找到对应的字段属性
            DataSource dataSource = jdbcTemplateFactory.get(sqlDefine.getDatasource()).getDataSource();
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(querySql);
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData srsmd = resultSet.getMetaData();
            ColumnParam field = null;
            for (int index = 1; index < srsmd.getColumnCount() + 1; index++) {
                field = this.createColumnParam(srsmd,index);

                //主表包含列
                if (masterFieldMap.containsKey(field.getField().toUpperCase())) {
                    field.setUpdateType(SqlDefineConstant.MODIFTY_HIDE);
                    field.setInsert(true);
                    field.setVisible(true);
                    field.setCardVisible(true);
                    field.setSwitchable(true);
                    field.setView(true);
                }
                list.add(field);
            }
            return list;
        }catch (Exception e){
            log.error("查询视图列信息异常",e);
            throw new ServiceException(StatusCodeEnum.SYSTEM_ERROR.getCode(),StatusCodeEnum.SYSTEM_ERROR.getMessage());
        }finally {
            this.destroyConnection(connection);
        }
    }

    private ColumnParam createColumnParam(ResultSetMetaData srsmd,int index) throws SQLException {
        ColumnParam field = new ColumnParam();
        field.setField(srsmd.getColumnLabel(index));// as 后的值 ，getColumnName 原始值
        field.setMaxlength(srsmd.getPrecision(index));
        field.setDataType(srsmd.getColumnTypeName(index));
        field.setFieldType(ComponentType.TEXT.getValue());
        field.setAlign(SqlDefineConstant.align_center);
        field.setHalign(SqlDefineConstant.align_center);
        field.setValign(SqlDefineConstant.valign_middle);
        field.setFalign(SqlDefineConstant.valign_middle);
        field.setUnduplicated(false);
        field.setIdx(index);

        //判断是否是日期类型
        if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_DATE)) {
            field.setFieldType(ComponentType.DATEPICKER.getValue());
        }
        if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_TEXT)) {
            field.setFieldType(ComponentType.TEXTAREA.getValue());
        }
        if (SimpleUtils.getDataType(field.getDataType()).equals(SqlDefineConstant.COLUMNTYPE_NUMBER)) {
            field.setAlign(SqlDefineConstant.align_right);
        }
        return field;
    }

    @Override
    public Response deleteByDataViewCode(String code, JSONObject rowValue) {
        DataView dataView = dataViewService.getByDataViewCode(code);

        //获取sqlDefine
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(dataView.getSqlId());
        if (StringUtils.isEmpty(sqlDefine.getMasterTableId())) {
            return Response.FAILURE("未配置主键");
        }

        //delete SQL
        StringBuffer deleteSql = new StringBuffer(" delete from ")
                .append(sqlDefine.getMasterTable())
                .append(" where ")
                .append(sqlDefine.getMasterTableId())
                .append(" = :")
                .append(sqlDefine.getMasterTableId());

        //参数绑定
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(sqlDefine.getMasterTableId(), rowValue.get(sqlDefine.getMasterTableId()));
        if (jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource()).update(deleteSql.toString(),
                paramMap) < 1) {
            return Response.FAILURE(deleteSql);
        }
        return Response.SUCCESS();
    }


    @Override
    public Response modifyByDataViewCode(String code, JSONObject rowValue) {
        DataView dataView = dataViewService.getByDataViewCode(code);

        //获取参数配置
        OptionsParam optionsParam = JSON.parseObject(dataView.getOptions(), OptionsParam.class);

        //获取sqlDefine
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(dataView.getSqlId());

        //是否包含主键
        if (StringUtils.isBlank(sqlDefine.getMasterTableId())) {
            return Response.FAILURE("未设置主键,SQLID:" + dataView.getSqlId());
        }

        //获取修改列
        List<ColumnParam> dataViewFields = JSON.parseArray(dataView.getColumns(), ColumnParam.class);
        if (CollectionUtils.isEmpty(dataViewFields)) {
            return Response.FAILURE("未设置字段列表,视图编号:" + dataView.getSqlId());
        }

        //update SQL
        StringBuffer modifysql = new StringBuffer(" update ").append(sqlDefine.getMasterTable()).append(" set ");

        //参数绑定
        Map<String, Object> paramMap = new HashMap<>();
        for (ColumnParam columnParam : dataViewFields) {
            if (SqlDefineConstant.MODIFTY_ENABLE.equals(columnParam.getUpdateType())) {
                if (this.unduplicated(columnParam, sqlDefine, rowValue, false)) {
                    return Response.FAILURE(columnParam.getTitle() + "数据重复");
                }
                modifysql.append(columnParam.getField())
                        .append("= :")
                        .append(columnParam.getField())
                        .append(",");
                paramMap.put(columnParam.getField(), rowValue.get(columnParam.getField()));
            }
        }
        if(paramMap.isEmpty()){
            return Response.FAILURE("请设置需要更新的字段");
        }

        //sql整理
        modifysql.deleteCharAt(modifysql.lastIndexOf(","));

        //sql条件处理
        StringBuffer whereSql = new StringBuffer().append(" where ")
                .append(sqlDefine.getMasterTableId()).append("= :")
                .append(sqlDefine.getMasterTableId());
        paramMap.put(sqlDefine.getMasterTableId(), rowValue.get(sqlDefine.getMasterTableId()));
        
        //根据版本号更新
        if (StringUtils.isNotBlank(optionsParam.getVersion())) {
            int version = (Integer)rowValue.get(optionsParam.getVersion()) + 1;
            //修改版本号
            modifysql.append(", ").append(optionsParam.getVersion()).append("  = :")
                    .append(optionsParam.getVersion());
            paramMap.put(optionsParam.getVersion(), version);

            //where条件添加版本
            whereSql.append(" and ").append(optionsParam.getVersion()).append("  < :")
                    .append(optionsParam.getVersion());
            paramMap.put(optionsParam.getVersion(), version);
        }
        modifysql = modifysql.append(whereSql);
        if (jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource()).update(modifysql.toString(),
                paramMap) < 1) {
            return Response.FAILURE(modifysql);
        }
        return Response.SUCCESS();
    }

    /**
     * 数据唯一校验
     */
    private boolean unduplicated(ColumnParam field, SqlDefine sqlDefine, JSONObject rowValue, boolean insert) {
        if (!field.isUnduplicated()) {
            return false;
        }
        Map<String, Object> checkParams = new HashedMap();
        StringBuffer checkSql = new StringBuffer("select count(1) from ").append(sqlDefine.getMasterTable());
        //新增
        if (insert) {
            checkSql.append(" t where ")
                    .append(" t.")
                    .append(field.getField())
                    .append(" = :").append(field.getField());
            checkParams.put(field.getField(), rowValue.get(field.getField()));
        } else {
            checkSql.append(" t where t.").append(sqlDefine.getMasterTableId())
                    .append(" <> :").append(sqlDefine.getMasterTableId())
                    .append(" and t.")
                    .append(field.getField())
                    .append(" = :").append(field.getField());
            checkParams.put(sqlDefine.getMasterTableId(), rowValue.get(sqlDefine.getMasterTableId()));
            checkParams.put(field.getField(), rowValue.get(field.getField()));
        }
        return jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                .queryForObject(checkSql.toString(),checkParams, Long.class) > 0;
    }

    @Override
    public Response createByDataViewCode(String code, JSONObject rowValue) {
        DataView dataView = dataViewService.getByDataViewCode(code);
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(dataView.getSqlId());

        //获取修改列
        List<ColumnParam> dataViewFields = JSON.parseArray(dataView.getColumns(), ColumnParam.class);
        if (CollectionUtils.isEmpty(dataViewFields)) {
            return Response.FAILURE("未设置字段列表,视图编号:" + dataView.getSqlId());
        }

        //不包含主键
        if (StringUtils.isBlank(sqlDefine.getMasterTableId())) {
            return Response.FAILURE("未设置主键,SQLID:" + dataView.getSqlId());
        }

        //create sql
        StringBuffer createsql = new StringBuffer("insert into ").append(sqlDefine.getMasterTable()).append("(");

        //列表达式
        StringBuffer expressionsql = new StringBuffer(") values (");

        //参数绑定
        Map<String, Object> paramMap = new HashMap<>();
        for (ColumnParam columnParam : dataViewFields) {
            if (!columnParam.isInsert()) {
                continue;
            }

            if (this.unduplicated(columnParam, sqlDefine, rowValue, true)) {
                return Response.FAILURE(columnParam.getTitle() + "数据重复");
            }
            createsql.append(columnParam.getField()).append(",");
            expressionsql.append(":").append(columnParam.getField()).append(",");
            paramMap.put(columnParam.getField(), rowValue.get(columnParam.getField()));
        }

        //添加主键列
        createsql.append(sqlDefine.getMasterTableId());

        //创建主键表达式
        expressionsql.append(":").append(sqlDefine.getMasterTableId()).append(")");

        //添加id
        String uuid = rowValue.getString(sqlDefine.getMasterTableId());
        if(StringUtils.isBlank(uuid)){
            paramMap.put(sqlDefine.getMasterTableId(), GUID.nextId());
        }else{
            paramMap.put(sqlDefine.getMasterTableId(), uuid);
        }

        //end sql
        String sql = createsql.append(expressionsql).toString();
        if (jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource()).update(sql, paramMap) < 1) {
            return Response.FAILURE(sql);
        }
        return Response.SUCCESS();
    }

    @Override
    public Response fetch(SqlDefineFetchParam sqlDefineFetchParam) {
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(sqlDefineFetchParam.getSqlId());

        //根据SQLid获取查询语句
        StringBuilder sqlBuilder = new StringBuilder("select t.* from (")
                .append(sqlDefine.getSelectSql()).append(") t ")
                .append(" where ").append(sqlDefine.getMasterTableId()).append("=").append(":id");
        Map<String, Object> paraMap = new HashedMap();
        paraMap.put("id", sqlDefineFetchParam.getId());
       
        //查询指定数据库的数据
        return Response.SUCCESS(jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                .queryForMap(sqlBuilder.toString(),paraMap));
    }

    @Override
    public Response<BootstrapPageResult> getBootstrapTableResponse(BootstrapSearchParam bootstrapSearchParam,
            String sqlId) {
        return this.getBootstrapTableResponse(bootstrapSearchParam.getPageSize(), bootstrapSearchParam.getPageNumber()
                , bootstrapSearchParam.getSearchText(), bootstrapSearchParam.getSortName()
                , bootstrapSearchParam.getSortOrder(), sqlId
                , bootstrapSearchParam);
    }

    /**
     * 默认获取bootstrapTable服务
     */
    @Override
    public Response<BootstrapPageResult> getBootstrapTableResponse(Integer pageSize, Integer pageNumber,
            String searchText,
            String sortName, String sortOrder, String sqlId,
            BootstrapSearchParam bootstrapSearchParam) {
        BootstrapPageResult pageResultForBootstrap = new BootstrapPageResult();
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(sqlId);
        DataFilter dataFilter = DataFilter.getInstance();
        dataFilter.setQuerySql(sqlDefine.getSelectSql());
        dataFilter.setSortName(sortName);
        dataFilter.setSortOrder(sortOrder);

        //条件
        List<ConditionParam> conditionParamList = bootstrapSearchParam.getSearchArray();
        if (CollectionUtils.isEmpty(conditionParamList)) {
            conditionParamList = new ArrayList<>();
        }

        //解析ztree
        ConditionParam ztreeConditionParam = this.getTreeNode(bootstrapSearchParam.getTreeOptions());
        if (null != ztreeConditionParam) {
            conditionParamList.add(ztreeConditionParam);
        }
        dataFilter.addCondition(conditionParamList);
        List<Map<String, Object>> list = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                .queryForList(dataFilter.createPager(pageNumber, pageSize), dataFilter.getParams());
        pageResultForBootstrap.setRows(list);

        //查询总数
        Long count = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                .queryForObject(dataFilter.countSql(), dataFilter.getParams(),Long.class);
        pageResultForBootstrap.setTotal(count);
        return Response.SUCCESS(pageResultForBootstrap);
    }


    /**
     * 获取树节点条件
     */
    private ConditionParam getTreeNode(TreeOptionsParam treeOptionsParam) {
        if (null == treeOptionsParam || !treeOptionsParam.isShow()) {
            return null;
        }

        //获取sqlDefine
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(treeOptionsParam.getSqlId());
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //默认是空字符串
        String idValue = StringUtils.isBlank(treeOptionsParam.getNodeValue()) ? BLANK_STR : treeOptionsParam.getNodeValue();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        switch (treeOptionsParam.getScope()) {
            case TreeNodeHandleType.TREEHANDLETYPE_ALL:
                result = findAllNode(warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getpIdKey()), idValue,
                        treeOptionsParam, sqlDefine.getDatasource());
                break;
            case TreeNodeHandleType.TREEHANDLETYPE_CHILD:
                paramMap.put(treeOptionsParam.getpIdKey(), idValue);
                result = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource()).queryForList(
                        warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getpIdKey()), paramMap);
                break;
            case TreeNodeHandleType.TREEHANDLETYPE_SELF:
                paramMap.put(treeOptionsParam.getIdKey(), idValue);
                result = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource()).queryForList(
                        warpTreeSql(sqlDefine.getSelectSql(), treeOptionsParam.getIdKey()), paramMap);
                break;
            default:
        }
        ConditionParam conditionDto = new ConditionParam();
        conditionDto.setField(treeOptionsParam.getForeignKey());
        conditionDto.setExpression(SqlExpression.IN);
        //TODO 换成in语句
        conditionDto.setValue(appendIds(result, treeOptionsParam.getIdKey()));
        return conditionDto;
    }

    /**
     * 拼接in字符
     */
    private String appendIds(List<Map<String, Object>> mapList, String key) {
        StringBuffer sb = new StringBuffer();
        for (Map<String, Object> item : mapList) {
            sb.append(item.get(key)).append(",");
        }
        return StringUtils.isBlank(sb) ? BLANK_STR: sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }

    /**
     * tree查询SQL
     */
    private String warpTreeSql(String sql, String field) {
        return new StringBuffer("select t.* from (").append(sql).append(") t where t.")
                .append(field)
                .append(" =:").append(field).toString();
    }

    /**
     * 根据nodeId获取所有子节点
     */
    private List<Map<String, Object>> findAllNode(String sql, Object pId, TreeOptionsParam treeVo, String dataSource) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(treeVo.getpIdKey(), pId);
        List<Map<String, Object>> queryResult = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> result = jdbcTemplateFactory.getNamedParameterJdbcTemplate(dataSource).queryForList(sql,
                paramMap);
        if (!org.springframework.util.CollectionUtils.isEmpty(result)) {
            List<Map<String, Object>> subResult = null;
            for (Map<String, Object> subMap : result) {
                subResult = findAllNode(sql, subMap.get(treeVo.getIdKey()), treeVo, dataSource);
                if (!org.springframework.util.CollectionUtils.isEmpty(subResult))
                    queryResult.addAll(subResult);
            }
            queryResult.addAll(result);
        }
        return queryResult;
    }

    @Override
    public Response ztree(ZtreeParamsRequest ztreeParamsRequest) {
        SqlDefine sqlDefine = sqlDefineRepository.findBySqlId(ztreeParamsRequest.getSqlId());
        StringBuilder sqlBuilder = new StringBuilder("select t.* from (");
        sqlBuilder.append(sqlDefine.getSelectSql()).append(") t ");
        Map<String, Object> paraMap = new HashedMap();

        //异步加载
        if (ztreeParamsRequest.isEnable()) {
            sqlBuilder.append(" where t.");
            if (StringUtils.isBlank(ztreeParamsRequest.getId())) {
                sqlBuilder.append(ztreeParamsRequest.getpIdKey()).append(" = ''");
            } else {
                sqlBuilder.append(ztreeParamsRequest.getpIdKey()).append("= :parent");
                paraMap.put("parent", ztreeParamsRequest.getId());
            }
        }
        List<Map<String, Object>> dataList = jdbcTemplateFactory.getNamedParameterJdbcTemplate(
                sqlDefine.getDatasource()).queryForList(sqlBuilder.toString(), paraMap);

        //异步加载判断是否parent
        if (ztreeParamsRequest.isEnable()) {
            for (Map<String, Object> node : dataList) {
                node.put("isParent", this.isParent(node.get(ztreeParamsRequest.getIdKey()).toString(), sqlDefine,
                        ztreeParamsRequest));
            }
        }
        return Response.SUCCESS(dataList);
    }

    /**
     * TODO leaf 优化
     * 判断是否上级节点
     */
    private boolean isParent(String parent, SqlDefine sqlDefine, ZtreeParamsRequest ztreeParamsRequest) {
        StringBuilder stringBuilder = new StringBuilder("select t.* from (")
                .append(sqlDefine.getSelectSql())
                .append(") t where t.")
                .append(ztreeParamsRequest.getpIdKey())
                .append("= :parent");

        Map<String, Object> paraMap = new HashedMap();
        paraMap.put("parent", parent);
        List<Map<String, Object>> dataList = jdbcTemplateFactory.getNamedParameterJdbcTemplate(sqlDefine.getDatasource())
                        .queryForList(stringBuilder.toString(), paraMap);
        return dataList.size() > 0;
    }


    @Override
    public List<FindTablesResult> findTables(FindTablesParam findTablesParam) {
        Connection connection = null;
        try {
            List<FindTablesResult> dataList = new ArrayList<>();
            if (StringUtils.isBlank(findTablesParam.getTablename())) {
                return dataList;
            }
            JdbcTemplate jdbcTemplate = jdbcTemplateFactory.get(findTablesParam.getDatasource());
            connection = jdbcTemplate.getDataSource().getConnection();
            String sql = "select table_name,table_comment from information_schema.tables " +
                    "where table_schema= ?  and TABLE_NAME like ? ";

            LinkedHashMap<String, Object> paraMap = new LinkedHashMap();

            //get dbname
            paraMap.put("tableschema", connection.getCatalog());
            paraMap.put("tableName", findTablesParam.getTablename() + '%');
            dataList = jdbcTemplate.query(sql, SimpleUtils.linkedHashMapToValues(paraMap), new RowMapper<FindTablesResult>() {

                        @Override
                        public FindTablesResult mapRow(ResultSet resultSet, int i) throws SQLException {
                            FindTablesResult tablesResult = new FindTablesResult();
                            tablesResult.setTableName(resultSet.getString("table_name"));
                            tablesResult.setTableComment(resultSet.getString("table_comment"));
                            return tablesResult;
                        }
                    });
            return dataList;
        }catch (Exception e){
            log.error("查询表列表异常",e);
            throw new ServiceException(StatusCodeEnum.SYSTEM_ERROR.getCode(),StatusCodeEnum.SYSTEM_ERROR.getMessage());
        }finally {
            this.destroyConnection(connection);
        }
    }

    @Override
    public List<GetColumnsResult> getColumns(String tablename, String datasource){
        Connection connection = null;
        try {
            String sql = "SELECT t.column_name,t.column_key,t.column_comment FROM information_schema.COLUMNS t where t.table_name = ? and t.table_schema = ? ";
            LinkedHashMap<String, Object> paraMap = new LinkedHashMap();
            paraMap.put("tableName", tablename);
            JdbcTemplate jdbcTemplate = jdbcTemplateFactory.get(datasource);
            connection = jdbcTemplate.getDataSource().getConnection();
            paraMap.put("tableschema", connection.getCatalog());
            List<GetColumnsResult> dataList = jdbcTemplate.query(sql, SimpleUtils.linkedHashMapToValues(paraMap),
                    new RowMapper<GetColumnsResult>() {
                        @Override
                        public GetColumnsResult mapRow(ResultSet resultSet, int i) throws SQLException {
                            GetColumnsResult mySQLColumnResult = new GetColumnsResult();
                            mySQLColumnResult.setColumnName(resultSet.getString("column_name"));
                            mySQLColumnResult.setColumnKey(resultSet.getString("column_key"));
                            mySQLColumnResult.setColumnComment(resultSet.getString("column_comment"));
                            return mySQLColumnResult;
                        }
                    });
            return dataList;
        }catch (Exception e){
            log.error("查询列信息异常",e);
            throw new ServiceException(StatusCodeEnum.SYSTEM_ERROR.getCode(),StatusCodeEnum.SYSTEM_ERROR.getMessage());
        }finally {
            this.destroyConnection(connection);
        }
    }

    //释放数据库连接
    private void destroyConnection(Connection connection){
        try {
            if(null != connection){
                connection.close();
            }
        } catch (SQLException e) {
            log.error("数据库连接释放异常",e);
        }
    }
}
