package com.ubold.admin.bootstrap;

import org.hibernate.SQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/6/5.
 */
@Service
public class BootstrapCriteriaQuery {

    @PersistenceContext
    EntityManager entityManager;

    // bean属性映射
    public <T> Page<T> findAll(String sql, Class<?> clazz, int page, int size){
        return this.findAll(sql,clazz,page,size,(Sort)null);
    }

    // bean属性映射
    public <T> Page<T> findAll(String sql, Class<?> clazz, int page, int size, Sort sort){
        List<T> list = null;

        //查询记录条数
        String countSql = new StringBuffer("select count(1) as cnt from (" ).append(sql).append( ") t ").toString();

        //创建查询对象
        Query countQuery = entityManager.createNativeQuery(countSql);

        //获取总记录数
        Object totalCount = countQuery.getSingleResult();

        //分页查询
        Query nativeQuery = entityManager.createNativeQuery(sql);

        //当前页总记录数
        nativeQuery.setFirstResult(page * size);

        //每页数量数
        nativeQuery.setMaxResults(size);
        try {
            list = nativeQuery.unwrap(SQLQuery.class)
//                        .setResultTransformer(Transformers.aliasToBean(clazz))
                    .setResultTransformer(new IgnoreCaseResultTransformer(clazz))
                    .list();
        } finally {

            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        //设置分页信息
        return new PageImpl<T>(list, new PageRequest(page, size, sort), Long.valueOf(totalCount.toString()));
    }

    // bean属性映射
    public <T> List<T> findAll(String sql, Class<?> clazz, Map<String,Object> paraMap){
        List<T> list = null;
        try {
            Query nativeQuery = entityManager.createNativeQuery(sql);
            paraMap.forEach((k,v)-> {
                nativeQuery.setParameter(k,v);
            });
            list = nativeQuery.unwrap(SQLQuery.class)
                    .setResultTransformer(new IgnoreCaseResultTransformer(clazz))
                    .list();
        } finally {

            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }
}
