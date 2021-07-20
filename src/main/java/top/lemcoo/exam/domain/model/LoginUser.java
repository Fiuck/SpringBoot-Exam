package top.lemcoo.exam.domain.model;

import lombok.Data;
import top.lemcoo.exam.domain.entity.SysUser;

import java.util.Set;

/**
 * @author zhaowx
 * @Description
 * @date 2021/7/20
 */
@Data
public class LoginUser {

    /**
     * token
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 失效时间
     */
    private Long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permission;

    /**
     * 用户信息
     */
    private SysUser user;
}
