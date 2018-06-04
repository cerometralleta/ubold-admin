package com.ubold.admin.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * RedisCacheConfig Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 19, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testStringRedisTemplate() throws Exception {
        // 保存字符串
//        stringRedisTemplate.opsForValue().set("aaa", "applicationSessionId:administrator");
        stringRedisTemplate.opsForValue().increment("aaa",1);
        stringRedisTemplate.expire("aaa",10, TimeUnit.SECONDS);
        stringRedisTemplate.boundValueOps("aaaaaaaaa").increment(1);

        System.out.println(stringRedisTemplate.opsForValue().get("applicationSessionId:administrator"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("applicationSessionId:administrator"));
    }

//    @Test
//    public void testRedisTemplate() throws Exception {
//        // 保存对象
//        User user = new User("超人", 20);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//        user = new User("蝙蝠侠", 30);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//        user = new User("蜘蛛侠", 40);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//        System.out.println(redisTemplate.opsForValue().get("超人"));
//        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());
//    }
}

class User implements Serializable {
    private String username;
    private Integer age;

    public User(String name, Integer age) {
        this.username = name;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}