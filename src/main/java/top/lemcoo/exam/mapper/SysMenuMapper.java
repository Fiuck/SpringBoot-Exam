package top.lemcoo.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lemcoo.exam.domain.entity.SysMenu;

import java.util.List;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/7/21 0021 9:20
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID获取菜单权限
     *
     * @param userId
     * @return
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID获取菜单树信息
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
