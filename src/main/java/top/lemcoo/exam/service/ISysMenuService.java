package top.lemcoo.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.lemcoo.exam.domain.entity.SysMenu;

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
}
