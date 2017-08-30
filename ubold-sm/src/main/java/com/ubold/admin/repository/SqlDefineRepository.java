package com.ubold.admin.repository;

import com.ubold.admin.domain.SqlDefine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */
@Repository
public interface SqlDefineRepository extends JpaRepository<SqlDefine, String> {

    Page<SqlDefine> findAll(Pageable pageable);
    SqlDefine findBySqlId(String sqlId);
    List<SqlDefine> findBySqlIdAndIdNot(String sqlId,String id);
}
