package com.ubold.admin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="TB_USER_INFO")
public class UserInfo extends User{
}
