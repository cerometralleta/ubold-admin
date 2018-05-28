package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
@Entity
@Table(name="TB_USER_INFO")
public class UserInfo extends User{
}
