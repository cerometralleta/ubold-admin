package com.ubold.admin.repository;

import com.ubold.admin.domain.DataView;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by zkning on 2017/8/13.
 */
@Repository
public interface DataViewRepository extends JpaRepository<DataView, String>{

    List<DataView> findByDataViewCode(String dataViewCode);
    List<DataView> findByDataViewCodeAndIdNot(String dataViewCode,String id);
}
