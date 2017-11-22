package com.ubold.admin.repository;

import com.ubold.admin.domain.Functional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Repository
public interface FunctionalRepository extends JpaRepository<Functional,String> {

    @Query(value = "select rf.* from tb_rbac_func rf ,tb_rbac_permission_func rpf where rf.id = rpf.func_id and rpf.permission_id in (?1);",nativeQuery = true)
    List<Functional> findFunctionalByPermissions(String permissions);
}
