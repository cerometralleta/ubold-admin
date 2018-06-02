package com.ubold.admin.repository;

import com.ubold.admin.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/11/10.
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, String>,JpaSpecificationExecutor<UserInfo> {

    UserInfo findByUsername(String userName);

    UserInfo findByUsernameAndPassword(String userName, String password);
}
