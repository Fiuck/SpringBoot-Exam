package top.lemcoo.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/login")
    public R<String> login(@RequestBody LoginBody loginBody){
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return R.ok(token);
    }
}