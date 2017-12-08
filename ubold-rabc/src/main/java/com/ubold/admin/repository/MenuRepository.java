package com.ubold.admin.repository;

import com.ubold.admin.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,String>{

    @Query(value = "select rm.* from tb_rbac_menu rm ,tb_rbac_permission_menu rpm where rm.id = rpm.menu_id and rpm.permission_id in (?1)",nativeQuery = true)
    List<Menu> findMenuByPermissions(String permissions);
}
