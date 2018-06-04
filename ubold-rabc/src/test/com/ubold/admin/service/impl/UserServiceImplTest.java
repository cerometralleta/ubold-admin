package com.ubold.admin.service.impl;

import com.ubold.admin.RabcApplication;
import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.pager.CriteriaQueryPager;
import com.ubold.admin.repository.UserRepository;
import com.ubold.admin.service.UserService;
import com.ubold.admin.util.GUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/6/3.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabcApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CriteriaQueryPager criteriaQueryPager;

    @Test
    public void customQueryList() throws Exception {
        List<UserInfo>  list =  userService.customQueryList("admin","123456");
        list.forEach((UserInfo userInfo) -> {
            log.info("user name {}",userInfo.getName());
        });
    }

    @Test
    public void customQueryListPage(){
        Page<UserInfo> pager =  userService.customQueryListPage("admin","123456");
        pager.getContent().forEach((UserInfo userInfo) -> {
            log.info("用户名 {}",userInfo.getName());
            log.info("总记录数 {}",pager.getTotalElements());
        });
    }

    @Test
    public void customQueryList2Page(){

        String sql = "select *  from TB_USER_INFO where username = :username";
//        Page<UserInfo> pager =  criteriaQueryPager.find(sql,UserInfo.class,1,50);
//            criteriaQueryPager.find(sql,UserInfo.class,1,50);
        Map<String,Object> param = new HashedMap();
        param.put("username","2222");
      List<UserInfo> list =  criteriaQueryPager.find(sql,UserInfo.class,param);
        list.forEach((UserInfo userInfo) -> {
            log.info("用户名 {}",userInfo.getUsername());
//            log.info("总记录数 {}",pager.getTotalElements());
        });

    }


    @Test
    public void add(){
        for(int i=0;i<500;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(GUID.nextId());
            userInfo.setUsername("bbbb");
            userRepository.save(userInfo);
        }

    }
}