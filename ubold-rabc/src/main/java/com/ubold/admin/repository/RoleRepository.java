package com.ubold.admin.repository;

import com.ubold.admin.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/11/11.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,String>{


}
