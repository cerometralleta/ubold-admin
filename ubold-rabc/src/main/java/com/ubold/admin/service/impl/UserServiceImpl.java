package com.ubold.admin.service.impl;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.repository.UserRepository;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import com.ubold.admin.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceService resourceService;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public UserInfo findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Response<UserInfo> findByUserNameAndPassword(String userName, String password) {
        UserInfo userInfo = userRepository.findByUsernameAndPassword(userName, password);
        if (null == userInfo) {
            return Response.FAILURE();
        }
        return Response.SUCCESS(userInfo);
    }


    //自定义sql
    @Override
    public List<UserInfo> customQueryList(String userName, String password) {
        StringBuffer sqlBuffer = new StringBuffer("select * from TB_USER_INFO t where 1=1 ");
        Map<String,Object> paraMap = new HashedMap();
        if(StringUtils.isNoneBlank(userName)){
            sqlBuffer.append(" and username = :username ");
            paraMap.put("username",userName);
        }
        if(StringUtils.isNoneBlank(password)){
            sqlBuffer.append(" and password = :password ");
            paraMap.put("password",password);
        }
        try {
            Query query = entityManager.createNativeQuery(sqlBuffer.toString(), UserInfo.class);
            paraMap.forEach((k, v) -> {
                query.setParameter(k, v);
            });
            return query.getResultList();
        }finally {

            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Page<UserInfo> customQueryListPage(String userName, String password) {
       Page<UserInfo> pager = userRepository.findAll(new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate usernameLike =
                        criteriaBuilder.like(root.get("username"),"%" + userName);
                Predicate passwordEq =
                        criteriaBuilder.equal(root.get("password"),password);

//                where or criteriaBuilder
//                criteriaQuery.where(usernameLike,passwordEq);
                return criteriaBuilder.and(usernameLike,passwordEq);
            }

//            分页从0开始
        },new PageRequest(0,50,new Sort(new String[]{"createTime"})));
        return pager;
    }
}
