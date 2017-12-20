package com.ubold.admin.security;

import com.ubold.admin.config.SecurityOaut2Configure;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by ningzuokun on 2017/12/8.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory connectionFactory;

     @Autowired
     SecurityOaut2Configure securityOaut2Configure;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.tokenStore(tokenStore());

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        logger.info("Initializing JWT with public key:\n" + securityOaut2Configure.getPublicKey());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(securityOaut2Configure.getPrivateKey());
        if(StringUtils.isNotBlank(securityOaut2Configure.getPublicKey())){
            converter.setVerifierKey(securityOaut2Configure.getPublicKey());
        }
        return converter;
    }
}
