package top.lemcoo.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.domain.entity.SysMenu;
import top.lemcoo.exam.mapper.SysMenuMapper;
import top.lemcoo.exam.service.ISysMenuService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单权限 业务处理
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:12
 */
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
        List<SysMenu> menuList = menuMapper.selectMenuTreeByUserId(userId);
        return getChildren(menuList, 0L);
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
            item.setChildrens(getChildren(item, menuList));
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
            item.setChildrens(getChildren(item, all));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getOrderNum() == null ? 0 : item1.getOrderNum()) - (item2.getOrderNum() == null ? 0 : item2.getOrderNum());
        }).collect(Collectors.toList());
    }
}
