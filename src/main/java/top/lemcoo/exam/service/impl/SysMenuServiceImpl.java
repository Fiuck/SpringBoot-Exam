package top.lemcoo.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.common.Constants;
import top.lemcoo.exam.common.ResultCode;
import top.lemcoo.exam.common.UserConstants;
import top.lemcoo.exam.common.exception.CustomException;
import top.lemcoo.exam.domain.entity.SysMenu;
import top.lemcoo.exam.domain.vo.MetaVO;
import top.lemcoo.exam.domain.vo.RouterVO;
import top.lemcoo.exam.mapper.SysMenuMapper;
import top.lemcoo.exam.service.ISysMenuService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限 业务处理
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:12
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper menuMapper;

    /**
     * 根据用户ID获取权限
     * @param userId 用户ID
     * @return
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();

        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID获取菜单树信息
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> children = null;
        try {
            List<SysMenu> menuList = menuMapper.selectMenuTreeByUserId(userId);
            children = getChildren(menuList, 0L);
        }catch (Exception e) {
            log.error("根据用户ID获取菜单树信息，异常：{}", e.getMessage());
            throw new CustomException("系统内部异常！",ResultCode.FAILED.getCode());
        }
        return children;
    }

    /**
     * 构建前端需要的菜单树
     *
     * @param menuList 菜单列表
     * @return {@link List<RouterVO>}
     */
    @Override
    public List<RouterVO> buildMenuTree(List<SysMenu> menuList) {
        return null;
    }

    /**
     * 构建前端需要的菜单
     *
     * @param menuList 菜单列表
     * @return {@link List<SysMenu>}
     */
    @Override
    public List<RouterVO> buildMenus(List<SysMenu> menuList) {
        List<RouterVO> routers = new LinkedList<>();
        for (SysMenu menu : menuList) {
            RouterVO router = new RouterVO();
            router.setHidden(menu.getVisible() == 0);
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon(), menu.getIsCache() == 0, menu.getPath()));
            List<SysMenu> children = menu.getChildren();
            if(!children.isEmpty() && children.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(children));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO routerVO = new RouterVO();
                routerVO.setPath(menu.getPath());
                routerVO.setComponent(menu.getComponent());
                routerVO.setName(StringUtils.capitalize(menu.getPath()));
                routerVO.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon(), menu.getIsCache() == 0, menu.getPath()));
                childrenList.add(routerVO);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(null);
                router.setPath("/inner");
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO routerVO = new RouterVO();
                String routerPath = StringUtils.replaceEach(menu.getPath(), new String[] { Constants.HTTP, Constants.HTTPS }, new String[] { "", "" });
                routerVO.setPath(routerPath);
                routerVO.setComponent(UserConstants.INNER_LINK);
                routerVO.setName(StringUtils.capitalize(routerPath));
                routerVO.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(routerVO);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu))
        {
            component = menu.getComponent();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenu menu)
    {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu)
    {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            routerPath = StringUtils.replaceEach(routerPath, new String[] { Constants.HTTP, Constants.HTTPS }, new String[] { "", "" });
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu)
    {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS);
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu)
    {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu)
    {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 根据父节点ID获取所有子节点
     *
     * @param menuList 菜单列表
     * @param parentId 传入的父节点ID
     * @return
     */
    private List<SysMenu> getChildren(List<SysMenu> menuList, long parentId) {
        return menuList.stream().filter(item ->
                item.getParentId().equals(parentId)
        ).map(item -> {
            item.setChildren(getChildren(item, menuList));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getOrderNum() == null ? 0 : item1.getOrderNum()) - (item2.getOrderNum() == null ? 0 : item2.getOrderNum());
        }).collect(Collectors.toList());
    }

    /**
     * 获取子分类
     *
     * @param root 父菜单
     * @param all 菜单列表
     * @return
     */
    private List<SysMenu> getChildren(SysMenu root, List<SysMenu> all) {
        return all.stream().filter(item ->
                item.getParentId().equals(root.getMenuId())
        ).map(item -> {
            item.setChildren(getChildren(item, all));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getOrderNum() == null ? 0 : item1.getOrderNum()) - (item2.getOrderNum() == null ? 0 : item2.getOrderNum());
        }).collect(Collectors.toList());
    }
}
