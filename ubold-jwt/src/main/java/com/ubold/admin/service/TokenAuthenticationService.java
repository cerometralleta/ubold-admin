package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.constant.StatusCodeEnum;
import com.ubold.admin.domain.User;
import com.ubold.admin.response.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ningzuokun on 2017/12/18.
 */
@Slf4j
@Service
public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 5;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "token";// 存放Token的Header Key
    static final String AUTHORITIES = "authorities";
    static final String USER_TOKEN = "userToken:";
    private static final String permit_prefix = "/permit/";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JwtLoginService jwtUserAuthService;

    // JWT生成方法
    public void addAuthentication(HttpServletResponse response, User user) {
         //过期时间
        long expirationTimes = System.currentTimeMillis() + EXPIRATIONTIME;
        // 生成JWT
        String JWTString = Jwts.builder()
                // 保存权限（角色）
                .claim(AUTHORITIES, "read,write")
                // 用户名写入标题
                .setSubject(user.getUsername())
                // 有效期设置
                .setExpiration(new Date(expirationTimes))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        //写入redis
//        stringRedisTemplate.opsForValue().set(this.userTokenCreate(user.getUsername()),
//                JSONObject.toJSONString(user),
//                expirationTimes - new Date().getTime(),
//                TimeUnit.MILLISECONDS);

        // 将 JWT 写入 body
        try {
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JSONObject.toJSONString(
                    Response.SUCCESS(jwtUserAuthService.getAccountCredentials(user, JWTString))));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Jwt token构建异常",e);
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String createtokenId(String username) {
        return USER_TOKEN + username;
    }

    public Claims httpServletRequestClaims(HttpServletRequest request) {

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
        return claims;
    }

    // JWT验证方法
    public Response<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getServletPath().indexOf(permit_prefix) > 0){
            Response.SUCCESS(new UsernamePasswordAuthenticationToken(null, null, null));
        }
        Claims claims = httpServletRequestClaims(httpServletRequest);
        if (null == claims) {

            //未登陆
            return Response.FAILURE(StatusCodeEnum.NEED_LOGIN.getCode(),StatusCodeEnum.NEED_LOGIN.getMessage());
        }
        // 拿用户名
        String username = claims.getSubject();

        //判断是否过期
        if (claims.getExpiration().getTime() - System.currentTimeMillis() < 0) {

            //Session失效
            return Response.FAILURE(StatusCodeEnum.SESSION_EXPIRED.getCode(),StatusCodeEnum.SESSION_EXPIRED.getMessage());
        }

        //校验redis subject用户账户,获取用户详细信息
//        String tokenInfoJson = stringRedisTemplate.opsForValue().get(this.createtokenId(username));
//        if (StringUtils.isBlank(tokenInfoJson)) {

             //Session失效
//          return Response.FAILURE(StatusCodeEnum.SESSION_EXPIRED.getCode(),StatusCodeEnum.SESSION_EXPIRED.getMessage());
//        }
        User user = new User();
        user.setId("01559ba349demTa8haRXHHT6FcIe0c98");
        user.setName("kunkun");
        user.setAvatar("/assets/tmp/img/avatar.jpg");
        user.setEmail("cipchk@qq.com");
        user.setUsername("kunkun");

        //url权限验证
        Map<String,String> authorityMap = jwtUserAuthService.getResources(user.getId());
        if(!authorityMap.containsKey(httpServletRequest.getServletPath())){

            //没权限
//            return Response.FAILURE(StatusCodeEnum.INSUFFICIENT_PRIVILEGES.getCode(),StatusCodeEnum.INSUFFICIENT_PRIVILEGES.getMessage());
        }
        // 得到权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));

        // 返回验证令牌
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = StringUtils.isNotBlank(username) ?
                new UsernamePasswordAuthenticationToken(username, user.getPassword(), authorities) :
                null;
        usernamePasswordAuthenticationToken.setDetails(user);
        return Response.SUCCESS(usernamePasswordAuthenticationToken);
    }

    public Response<User> getUserInfo(String userName, String password) {
        Response<? extends User> response = jwtUserAuthService.findByUserNameAndPassword(userName, password);
        if (!response.checkSuccess()) {
            return Response.FAILURE();
        }
        return Response.SUCCESS(response.getResult());
    }
}
