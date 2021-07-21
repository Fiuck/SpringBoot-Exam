package top.lemcoo.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.lemcoo.exam.domain.entity.SysUser;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:24
 */
@Component
public class SysPermissionService {

    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysMenuService menuService;

    /**
     * 获取菜单数据权限
     * @param user
     * @return
     */
    public Set<String> getMenuPermission(SysUser user){
        Set<String> perms = new HashSet<>();
        // 管理员角色拥有所有菜单权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
