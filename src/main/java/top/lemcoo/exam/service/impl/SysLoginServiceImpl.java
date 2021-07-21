package top.lemcoo.exam.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.common.ResultCode;
import top.lemcoo.exam.common.exception.BaseException;
import top.lemcoo.exam.common.exception.CustomException;
import top.lemcoo.exam.common.exception.UserPasswordNotMatchException;
import top.lemcoo.exam.domain.entity.SysUser;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.service.SysPermissionService;
import top.lemcoo.exam.utils.JwtTokenUtil;
import top.lemcoo.exam.service.ISysLoginService;
import top.lemcoo.exam.utils.ServletUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private SysPermissionService permissionService;

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

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @Override
    public R getInfo() {
        LoginUser loginUser = jwtTokenUtil.getLoginUser(ServletUtil.getRequest());
        if (loginUser == null) {
            throw new BaseException(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage());
        }
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> perms = permissionService.getMenuPermission(user);
        Map<String,Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roles", roles);
        map.put("perms", perms);
        return R.ok(map);
    }
}
