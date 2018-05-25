package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
@Entity
@Table(name="TB_USER_INFO")
public class UserInfo extends Auditable{

    private String password;
    private String username;
    
}
