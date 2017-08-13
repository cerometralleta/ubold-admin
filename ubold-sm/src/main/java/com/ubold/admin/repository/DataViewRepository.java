package com.ubold.admin.repository;

import com.ubold.admin.domain.DataView;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zkning on 2017/8/13.
 */
@Repository
public interface DataViewRepository extends JpaRepository<DataView, String>{

}
