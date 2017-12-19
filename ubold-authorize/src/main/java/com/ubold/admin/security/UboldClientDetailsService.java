package com.ubold.admin.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * 客户端类型
 * Created by ningzuokun on 2017/12/19.
 */
@Service
public class UboldClientDetailsService implements ClientDetailsService{
    //TODO 客户端配置信息

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        return null;
    }
}
