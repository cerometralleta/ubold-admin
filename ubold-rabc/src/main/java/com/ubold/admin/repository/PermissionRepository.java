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

    // 根据用户查询所有关联权限
    @Query(value = "select trp.* from tb_rbac_permission trp where EXISTS (select trup.id from tb_rbac_user_permission trup where trup.user_id = ?1 and trp.id = trup.permission_id )",nativeQuery = true)
    List<Permission> findPermissionByUserId(String userId);

    // 根据用户角色查询所有角色
    @Query(value = "select rp.* from tb_rbac_permission rp where EXISTS (select rrp.id from tb_rbac_role_permission rrp where EXISTS (select rr.id from tb_rbac_role rr where EXISTS (select rur.id from tb_rbac_user_role rur where rur.user_id = ?1 and rrp.role_id = rr.id)) and rp.id = rrp.permission_id)",nativeQuery = true)
    List<Permission> findPermissionByRoleUserId(String userId);
}
