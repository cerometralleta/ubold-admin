package com.ubold.admin.repository;

import com.ubold.admin.domain.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/10.
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

    List<UserInfo> findByUsername(String userName);
}
