package com.ubold.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kim on 2015/9/21.
 */
@Data
@MappedSuperclass
public class Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Version
    @ApiModelProperty(value = "版本号")
    private long version;

    @ApiModelProperty(value = "创建者")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改者")
    private String lastUpdateUser;

    @ApiModelProperty(value = "修改时间")
    private Date lastUpdateTime;

    @PrePersist
    public void prePersist() {
        Date currentTime = new Date();
        this.setCreateTime(currentTime);
        this.setCreateUser(getUserName());
        this.setLastUpdateTime(currentTime);
        this.setLastUpdateUser(getUserName());
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastUpdateTime(new Date());
        this.setLastUpdateUser(getUserName());
    }
    
    @JsonIgnore
    private String getUserName() {
        String username = null;
        try {
//            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
}