package com.ubold.admin.vo;

import com.ubold.admin.domain.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ningzuokun on 2018/3/26.
 */
public class AccountCredentialsResult implements Serializable {

    private List<Resource> resources;
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
