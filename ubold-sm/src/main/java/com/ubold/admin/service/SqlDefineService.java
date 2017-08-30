package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.repository.JpaRepository;
import com.ubold.admin.repository.SqlDefineRepository;
import com.ubold.admin.response.Response;
import com.ubold.admin.vo.ColumnVo;

import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */
public interface SqlDefineService  extends JpaRepository<SqlDefineRepository> {

    /**
     * 保存SQL
     * @param paramJson
     * @return
     */
    Response persistent(JSONObject paramJson);

    /**
     * 根据SQLID获取所有列信息
     * @param sqlId
     * @return
     */
    public List<ColumnVo> getColumnsBySqlId(String sqlId);
}
