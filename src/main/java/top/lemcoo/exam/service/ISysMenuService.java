package top.lemcoo.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.lemcoo.exam.domain.entity.SysMenu;
import top.lemcoo.exam.domain.vo.RouterVO;

import java.util.List;
import java.util.Set;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:11
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID获取权限
     * @param userId 用户ID
     * @return
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID获取菜单树信息
     *
     * @param userId 用户ID
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端需要的菜单树
     *
     * @param menuList 菜单列表
     * @return {@link List<SysMenu>}
     */
    List<RouterVO> buildMenuTree(List<SysMenu> menuList);

    /**
     * 构建前端需要的菜单
     *
     * @param menuList 菜单列表
     * @return {@link Object}
     */
    List<RouterVO> buildMenus(List<SysMenu> menuList);
}
