package com.ubold.admin.model;

import com.ubold.admin.domain.Resources;
import com.ubold.admin.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ningzuokun on 2018/3/26.
 */
@Data
public class CredentialsModel implements Serializable {

    private String token;
    private User user;
}


