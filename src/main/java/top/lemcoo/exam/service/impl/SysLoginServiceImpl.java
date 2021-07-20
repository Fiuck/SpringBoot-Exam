package top.lemcoo.exam.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.security.jwt.JwtTokenUtil;
import top.lemcoo.exam.service.SysLoginService;

import javax.annotation.Resource;

/**
 * @author zhaowx
 * @Description
 * @date 2021/7/20
 */
@Service
@Slf4j
public class SysLoginServiceImpl implements SysLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        // 用户验证
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        UserDetails user =(UserDetails) authentication.getPrincipal();
        // 生成token
        return jwtTokenUtil.generateToken(user);
    }
}
