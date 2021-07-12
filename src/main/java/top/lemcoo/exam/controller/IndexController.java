package top.lemcoo.exam.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【】
 *
 * @Author: zhaowx
 * @Date: 2021/6/27 17:12
 */
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController{

    @GetMapping
    public Object index(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
