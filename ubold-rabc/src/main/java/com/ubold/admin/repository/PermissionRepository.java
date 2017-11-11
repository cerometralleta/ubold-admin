package com.ubold.admin.repository;

import com.ubold.admin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,String>{

    // 根据用户查询所有角色
    @Query(value = "${findPermissionByUserId}",nativeQuery = true)
    List<Permission> findPermissionByUserId(String userId);

    // 根据用户角色查询所有角色
    @Query(value = "${findPermissionByRoleUserId}",nativeQuery = true)
    List<Permission> findPermissionByRoleUserId(String userId);
}
