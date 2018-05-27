package com.ubold.admin.factory;

import com.ubold.admin.constant.NamedParameterJdbcTemplateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ningzuokun on 2018/2/27.
 */
@Component
public class JdbcTemplateFactory {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 默认master数据源,数据视图crud操作根据sqldefine配置的datasouce执行
     */
    public JdbcTemplate get(String dataSourceCode) {
        if (null == dataSourceCode) {
            return jdbcTemplate;
        }
        switch (dataSourceCode) {
            case NamedParameterJdbcTemplateEnum.DataSourceCodeConstant.MASTER:
                return jdbcTemplate;
            default:
                return jdbcTemplate;
//                throw new ServiceException("namedParameterJdbcTemplate mismatching:"+dataSourceCode);
        }
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String dataSourceCode){
        JdbcTemplate jdbcTemplate = this.get(dataSourceCode);
        return new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }
}
