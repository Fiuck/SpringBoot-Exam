package top.lemcoo.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lemcoo.exam.domain.entity.SysRole;

import java.util.List;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:19
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
