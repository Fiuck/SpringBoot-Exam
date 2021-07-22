package top.lemcoo.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.domain.model.LoginBody;
import top.lemcoo.exam.service.ISysLoginService;

/**
 * 登录controller
 *
 * @author zhaowx
 * @Description
 * @date 2021/7/20
 */
@RestController
@RequestMapping("/auth")
public class SysLoginController {

    @Autowired
    private ISysLoginService loginService;

    /**
     * 登录接口
     *
     * @param loginBody
     * @return
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginBody loginBody){
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return R.ok(token);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public R getInfo(){
        return loginService.getInfo();
    }

    /**
     * 获取用户路由
     *
     * @return
     */
    @GetMapping("/routers")
    public R getRouters(){
        return loginService.getRouters();
    }
}
