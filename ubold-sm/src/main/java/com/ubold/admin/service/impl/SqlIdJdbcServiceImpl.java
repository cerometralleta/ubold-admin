package com.ubold.admin.service.impl;

import com.ubold.admin.service.SqlIdJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zkning
 */
@Service
public class SqlIdJdbcServiceImpl extends ApplicationObjectSupport implements SqlIdJdbcService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
