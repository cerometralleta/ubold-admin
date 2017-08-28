package com.ubold.admin.repository;

import com.ubold.admin.domain.FormView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/8/28.
 */
@Repository
public interface FormViewRepository extends JpaRepository<FormView, String> {
    List<FormView> findByCode(String code);
    List<FormView> findByCodeAndIdNot(String code,String id);
}
