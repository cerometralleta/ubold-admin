package com.ubold.admin.model;

import com.ubold.admin.domain.ResourceInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ningzuokun on 2018/3/26.
 */
@Data
public class AccountCredentialsResult implements Serializable {
    private List<ResourceInfo> resources;
    private Map<String, String> authority;
    private String tokenId;
}
