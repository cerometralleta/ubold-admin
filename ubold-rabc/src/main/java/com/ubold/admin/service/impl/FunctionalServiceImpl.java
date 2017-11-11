package com.ubold.admin.service.impl;

import com.ubold.admin.domain.Functional;
import com.ubold.admin.domain.Menu;
import com.ubold.admin.repository.FunctionalRepository;
import com.ubold.admin.service.FunctionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class FunctionalServiceImpl implements FunctionalService {

    @Autowired
    FunctionalRepository functionalRepository;

    @Override
    public List<Functional> findFunctionalByPermissions(String permissions) {
        return functionalRepository.findFunctionalByPermissions(permissions);
    }
}
