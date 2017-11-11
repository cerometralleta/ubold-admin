package com.ubold.admin.repository.impl;

import com.ubold.admin.repository.JpaBootRepository;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.data.repository.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ningzuokun on 2017/5/15.
 */
public class JpaBootRepositoryImpl<I extends Repository<?, ?>> extends ApplicationObjectSupport implements JpaBootRepository<I> {

    private static final long serialVersionUID = 1L;
    private Class<?> clazz;

    public JpaBootRepositoryImpl() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        if (types[0] instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) types[0];
            clazz = (Class<?>) type.getRawType();
        } else {
            clazz = (Class<?>) types[0];
        }
    }

    public I getRepository() {
        return (I) this.getApplicationContext().getBean(clazz);
    }
}
