package com.ubold.admin.repository;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by ningzuokun on 2017/5/15.
 */
public interface JpaBootRepository<I extends Repository<?, ?>> extends Serializable {

    I getRepository();
}
