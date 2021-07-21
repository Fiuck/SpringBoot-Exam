package top.lemcoo.exam.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.lemcoo.exam.domain.entity.SysUser;

import java.util.Collection;
import java.util.Set;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:29
 * @desc
 */
@Component
public class LoginUser implements UserDetails {

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

    public LoginUser(){}

    public LoginUser(SysUser user, Set<String> permission){
        this.user = user;
        this.permission = permission;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "token='" + token + '\'' +
                ", loginTime=" + loginTime +
                ", expireTime=" + expireTime +
                ", permission=" + permission +
                ", user=" + user +
                '}';
    }
}
