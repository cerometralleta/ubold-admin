package com.ubold.admin.domain;

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
    protected static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Version
    @ApiModelProperty(value = "版本号")
    private long version;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date lastUpdateTime;

//    @ApiModelProperty(value = "修改者")
//    private String lastUpdateUser;
//
//    @ApiModelProperty(value = "创建者")
//    private String createUser;

    @PrePersist
    public void prePersist() {
        Date currentTime = new Date();
        this.setCreateTime(currentTime);
        this.setLastUpdateTime(currentTime);
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastUpdateTime(new Date());
    }
}