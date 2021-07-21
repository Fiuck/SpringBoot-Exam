package top.lemcoo.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import top.lemcoo.exam.domain.entity.SysRole;

import java.util.Collection;
import java.util.Set;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:11
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}
