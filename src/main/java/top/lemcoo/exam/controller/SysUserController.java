package top.lemcoo.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.domain.dto.UserListDTO;
import top.lemcoo.exam.service.ISysUserService;

/**
 * @author zhaowx
 * @Description
 * @date 2021/8/1
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService userService;

    /**
     * 获取用户列表
     *
     * @param dto dto
     * @return {@link R}
     */
    @GetMapping("/list")
    public R list(UserListDTO dto){
        return userService.listPage(dto);
    }
}
