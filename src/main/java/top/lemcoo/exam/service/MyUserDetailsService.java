package top.lemcoo.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.common.UserStatus;
import top.lemcoo.exam.common.exception.BaseException;
import top.lemcoo.exam.domain.entity.SysUser;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.mapper.SysUserMapper;

/**
 * 用户登录 验证处理
 * @author zhaowx
 * @date 2021/6/24 0024 10:23
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        SysUser sysUser = userMapper.selectUser(username);
        if (sysUser == null) {
            log.info("登录用户：{}不存在！", username);
            throw new UsernameNotFoundException("登录用户：" + username + "不存在！");
        }
        if (UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())) {
            log.info("登录用户：{}已被删除！", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            log.info("登录用户：{}已被停用！", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        return new LoginUser(sysUser, permissionService.getMenuPermission(sysUser));
    }
}
