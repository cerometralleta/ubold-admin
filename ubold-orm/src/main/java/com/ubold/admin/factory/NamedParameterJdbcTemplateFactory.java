package com.ubold.admin.factory;

import com.ubold.admin.constant.NamedParameterJdbcTemplateEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ningzuokun on 2018/2/27.
 */
@Component
public class NamedParameterJdbcTemplateFactory {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 默认master数据源,数据视图crud操作根据sqldefine配置的datasouce执行
     */
    public NamedParameterJdbcTemplate get(Integer dataSourceCode) {
        if (null == dataSourceCode) {
            return namedParameterJdbcTemplate;
        }
        switch (dataSourceCode) {
            case NamedParameterJdbcTemplateEnum.DataSourceCodeConstant.MASTER:
                return namedParameterJdbcTemplate;
            default:
                return namedParameterJdbcTemplate;
//                throw new ServiceException("namedParameterJdbcTemplate mismatching:"+dataSourceCode);
        }
    }
}
