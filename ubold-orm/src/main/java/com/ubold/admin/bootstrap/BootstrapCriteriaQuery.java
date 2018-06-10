package com.ubold.admin.bootstrap;

import org.hibernate.SQLQuery;
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
    public <T> Pager<T> findAll(String sql, Class<?> clazz, int pageNo, int pageSize){

        //查询记录条数
        String countSql = new StringBuffer("select count(1) as cnt from (" ).append(sql).append( ") t ").toString();

        //创建查询对象
        Query countQuery = entityManager.createNativeQuery(countSql);

        //获取总记录数
        Object totalCount = countQuery.getSingleResult();

        //分页查询
        Query nativeQuery = entityManager.createNativeQuery(sql);

        //当前页总记录数
        nativeQuery.setFirstResult(pageNo * pageSize);

        //每页数量数
        nativeQuery.setMaxResults(pageSize);
        try {
            List<T> list = nativeQuery.unwrap(SQLQuery.class)
                    .setResultTransformer(new IgnoreCaseResultTransformer(clazz))
                    .list();

            //设置分页信息
            return new Pager(pageNo,pageSize,Long.valueOf(totalCount.toString()),list);
        } finally {

            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    // bean属性映射
    public <T> List<T> findAll(String sql, Class<?> clazz, Map<String,Object> paraMap){
        try {
            Query nativeQuery = entityManager.createNativeQuery(sql);
            paraMap.forEach((k,v)-> {
                nativeQuery.setParameter(k,v);
            });
            return nativeQuery.unwrap(SQLQuery.class)
                    .setResultTransformer(new IgnoreCaseResultTransformer(clazz))
                    .list();
        } finally {

            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }
}
