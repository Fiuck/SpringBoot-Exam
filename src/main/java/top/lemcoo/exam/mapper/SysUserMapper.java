package top.lemcoo.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.lemcoo.exam.domain.entity.SysUser;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:25
 * @desc
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUser(String username);
}
