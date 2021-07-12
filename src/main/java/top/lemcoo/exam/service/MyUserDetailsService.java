package top.lemcoo.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.domain.SysRole;
import top.lemcoo.exam.domain.SysUser;
import top.lemcoo.exam.mapper.SysUserMapper;
import top.lemcoo.exam.security.jwt.JwtUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:23
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        SysUser sysUser = userMapper.selectUser(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        List<SimpleGrantedAuthority> collect = sysUser.getRoles().stream()
                .map(SysRole::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new JwtUser(sysUser.getUsername(), sysUser.getPassword(), collect);
    }
}
