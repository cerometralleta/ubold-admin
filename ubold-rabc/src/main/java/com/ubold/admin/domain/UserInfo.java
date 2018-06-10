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
@Table(name="t_user_info")
public class UserInfo extends User{
}
