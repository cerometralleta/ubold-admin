package com.ubold.admin.repository;

import com.ubold.admin.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/10.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsername(String userName);
}
