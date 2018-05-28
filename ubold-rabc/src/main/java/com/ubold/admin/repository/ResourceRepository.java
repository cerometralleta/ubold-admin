package com.ubold.admin.repository;

import com.ubold.admin.domain.Resource;
import com.ubold.admin.domain.ResourceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Repository
public interface ResourceRepository extends JpaRepository<ResourceInfo, String> {


    // 根据用户角色查询所有资源
    @Query(value = "select trr.* from tb_rbac_resource trr where EXISTS (select ts.resource_id from (select trrrr.resource_id from tb_rbac_role_resource_relation trrrr \n" +
            "where EXISTS (select trrur.role_id from tb_rbac_role_user_relation trrur where trrur.user_id = ?1 and trrur.role_id = trrrr.role_id)) ts where ts.resource_id = trr.id)  ", nativeQuery = true)
    List<Resource> findResourceByRoleUserId(String userId);


    @Query(value = "select trr.* from tb_rbac_resource trr where EXISTS (select ts.resource_id from (select trrrr.resource_id from tb_rbac_role_resource_relation trrrr \n" +
            "where EXISTS (select trrur.role_id from tb_rbac_role_user_relation trrur where trrur.user_id = ?1 and " +
            "trrur.role_id = trrrr.role_id)) ts where ts.resource_id = trr.id) and type = ?2 ", nativeQuery = true)
    List<Resource> findResourceByRoleUserIdAndType(String userId, Integer type);
}
