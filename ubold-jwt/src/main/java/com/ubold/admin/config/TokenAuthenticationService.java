package com.ubold.admin.config;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by ningzuokun on 2017/12/18.
 */
@Service
public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 432_000_000;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // JWT生成方法
      void addAuthentication(HttpServletResponse response, String username) {
         //过期时间
        long expirationTimes = System.currentTimeMillis() + EXPIRATIONTIME;
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", "read,write")
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(expirationTimes))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //写入redis
//         stringRedisTemplate.opsForValue().set(JWT,JWT,expirationTimes - new Date().getTime());
        // 将 JWT 写入 body
        try {
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JSONObject.toJSONString(Response.SUCCESS(JWT)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // JWT验证方法
      Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        // 解析 Token
        Claims claims = Jwts.parser()
                // 验签
                .setSigningKey(SECRET)
                // 去掉 Bearer
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
        // 拿用户名
        String user = claims.getSubject();
          //判断是否过期
          if (claims.getExpiration().getTime() - System.currentTimeMillis() < 0) {
              return null;
          }
        // 得到 权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
          //校验redis token是否过期或者已登出
//          if (StringUtils.isBlank(stringRedisTemplate.opsForValue().get(token))) {
//              return null;
//          }
        // 返回验证令牌
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  StringUtils.isNotBlank(user) ?
                new UsernamePasswordAuthenticationToken(user, null, authorities) :
                null;
        return usernamePasswordAuthenticationToken;
    }
}
