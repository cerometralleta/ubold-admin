package com.ubold.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;
import com.ubold.admin.util.SpringContextUtil;
import com.ubold.admin.vo.AccountCredentialsResult;
import com.ubold.admin.vo.GetMenuResult;
import com.ubold.admin.vo.TokenInfo;

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
import java.util.concurrent.TimeUnit;

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
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 5;     // 5天
    static final String SECRET = "P@ssw02d";            // JWT密码
    static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    static final String AUTHORITIES = "authorities";
    static final String USER_TOKEN = "userToken:";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // JWT生成方法
    public void addAuthentication(HttpServletResponse response, TokenInfo tokenInfo) {
         //过期时间
        long expirationTimes = System.currentTimeMillis() + EXPIRATIONTIME;
        // 生成JWT
        String JWTString = Jwts.builder()
                // 保存权限（角色）
                .claim(AUTHORITIES, "read,write")
                // 用户名写入标题
                .setSubject(tokenInfo.getUsername())
                // 有效期设置
                .setExpiration(new Date(expirationTimes))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //写入redis
        stringRedisTemplate.opsForValue().set(this.userTokenCreate(tokenInfo.getUsername()),
                JSONObject.toJSONString(tokenInfo),
                expirationTimes - new Date().getTime(),
                TimeUnit.MILLISECONDS);

        // 将 JWT 写入 body
        try {
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JSONObject.toJSONString(
                    Response.SUCCESS(this.getAccountCredentialsResult(tokenInfo.getUserId(), JWTString))));
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

    private AccountCredentialsResult getAccountCredentialsResult(String userId, String JWToken) {
        //获取用户菜单
        AccountCredentialsResult accountCredentialsResult = new AccountCredentialsResult();
        accountCredentialsResult.setTokenId(JWToken);
        ResourceService resourceService = SpringContextUtil.getBean(ResourceService.class);
        Response<GetMenuResult> response = resourceService.getMenuList(userId);
        if (response.checkSuccess()) {
            accountCredentialsResult.setResources(response.getResult().getResources());
        }
        accountCredentialsResult.setAuthority(resourceService.getAuthority(userId));
        return accountCredentialsResult;
    }

    private String userTokenCreate(String username) {
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
    public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
        Claims claims = httpServletRequestClaims(httpServletRequest);
        if (null == claims) {
            return null;
        }
        // 拿用户名
        String username = claims.getSubject();
        //判断是否过期
        if (claims.getExpiration().getTime() - System.currentTimeMillis() < 0) {
            return null;
        }
        // 得到 权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));

        //校验redis subject用户账户,获取用户详细信息
        String tokenInfoJson = stringRedisTemplate.opsForValue().get(this.userTokenCreate(username));
        if (StringUtils.isBlank(tokenInfoJson)) {
            return null;
        }
        TokenInfo tokenInfo = JSONObject.parseObject(tokenInfoJson, TokenInfo.class);
        // 返回验证令牌
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = StringUtils.isNotBlank(username) ?
                new UsernamePasswordAuthenticationToken(username, tokenInfo.getPassword(), authorities) :
                null;
        usernamePasswordAuthenticationToken.setDetails(tokenInfo);
        return usernamePasswordAuthenticationToken;
    }
}
