package com.ubold.admin.service.impl;

import com.ubold.admin.RabcApplication;
import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.repository.UserRepository;
import com.ubold.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
            log.info("user name {}",userInfo.getName());
        });
    }

    @Test
    public void add(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("bbbb");
        userRepository.save(userInfo);
    }
}