package com.ubold.admin.model;

import com.ubold.admin.domain.ResourceInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 授权结果
 * Created by ningzuokun on 2017/12/21.
 */
@Data
public class GetMenuResult implements Serializable {

    private List<ResourceInfo> resources;
}
