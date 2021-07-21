package top.lemcoo.exam.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.lemcoo.exam.common.Constants;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.utils.uuid.IdUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 【生成令牌工具类】
 *
 * @Author: zhaowx
 * @Date: 2021/6/17 21:00
 */
@Data
@Component
public class JwtTokenUtil implements Serializable {

    /** 令牌秘钥 */
    @Value("${jwt.secret}")
    private String secret;

    /** 令牌有效期（默认30分钟） */
    @Value("${jwt.expiration}")
    private Long expiration;

    /** 自定义令牌标识 */
    @Value("${jwt.header}")
    private String header;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request){
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)){
            Claims claims = getClaimsFromToken(token);
            // 解析对应的权限及用户信息
            String uuid = claims.get(Constants.LOGIN_USER_KEY, String.class);
            String userKey = getTokenKey(uuid);
            return (LoginUser) redisUtil.get(userKey);
        }
        return null;
    }

    /**
     * 设置用户身份信息
     *
     * @param loginUser
     */
    public void setLoginUser(LoginUser loginUser){
        if (loginUser != null && StringUtils.isNotBlank(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     *
     * @param token
     */
    public void delLoginUser(String token){
        if (StringUtils.isNotBlank(token)) {
            String userKey = getTokenKey(token);
            redisUtil.del(userKey);
        }
    }

    /**
     * 生成令牌
     *
     * @param loginUser 用户
     * @return 令牌
     */
    public String generateToken(LoginUser loginUser){
        // 生成uuid
        String uuid = IdUtil.fastUUID();
        loginUser.setToken(uuid);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Constants.LOGIN_USER_KEY,uuid);
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser){
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if ((expireTime - currentTime) <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 原令牌
     * @return 新令牌
     */
    public void refreshToken(LoginUser loginUser) {
        String refreshedToken;
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expiration + MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());

        redisUtil.set(userKey,loginUser,expiration, TimeUnit.MINUTES);
    }

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 从令牌中获取用户
     * @param token 令牌
     * @return 用户
     */
    public String getUsernameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        LoginUser user = (LoginUser) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 从请求中获取token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        String token = request.getHeader(header);

        if (StringUtils.isNotBlank(token) && token.startsWith(Constants.TOKEN_PREFIX)){
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid){
        return Constants.LOGIN_USER_KEY + uuid;
    }
}
