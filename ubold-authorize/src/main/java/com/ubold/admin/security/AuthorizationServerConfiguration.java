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
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Created by ningzuokun on 2017/12/8.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    SecurityOaut2Configure securityOaut2Configure;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("uboldClientId")
                .secret("secret")
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                .scopes("user_info")
                .autoApprove(true)
                .accessTokenValiditySeconds(90)
                .refreshTokenValiditySeconds(90);// 1 hour
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        .tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
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
