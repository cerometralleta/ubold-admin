package com.ubold.admin.service;

import com.ubold.admin.domain.Functional;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
public interface FunctionalService {

    List<Functional> findFunctionalByPermissions(String permissions);
}
