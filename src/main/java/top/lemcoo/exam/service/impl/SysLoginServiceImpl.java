package top.lemcoo.exam.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.common.exception.CustomException;
import top.lemcoo.exam.common.exception.UserPasswordNotMatchException;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.utils.JwtTokenUtil;
import top.lemcoo.exam.service.ISysLoginService;

import javax.annotation.Resource;

/**
 * @author zhaowx
 * @Description
 * @date 2021/7/20
 */
@Service
@Slf4j
public class SysLoginServiceImpl implements ISysLoginService {

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
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser user =(LoginUser) authentication.getPrincipal();
        // TODO 记录登录信息
        // 生成token
        return jwtTokenUtil.generateToken(user);
    }
}
