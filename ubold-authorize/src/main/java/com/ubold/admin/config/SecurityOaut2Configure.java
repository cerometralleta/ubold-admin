package com.ubold.admin.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ningzuokun on 2017/12/20.
 */
@Configuration
@ConfigurationProperties(prefix="security.oauth2")
public class SecurityOaut2Configure {

    @NotBlank(message = "privateKey must not be null, empty, or blank")
    private String privateKey;
    private String publicKey;

    /**
     * authorizty context
     */
    private String authorityContext;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAuthorityContext() {
        return authorityContext;
    }

    public void setAuthorityContext(String authorityContext) {
        this.authorityContext = authorityContext;
    }
}
