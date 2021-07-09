package top.lemcoo.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/7/9 0009 15:56
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String index(){
        return "jenkins部署";
    }
}
