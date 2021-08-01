package top.lemcoo.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.domain.dto.UserListDTO;
import top.lemcoo.exam.domain.entity.SysUser;

/**
 * 系统用户服务
 *
 * @author zhaowx
 * @Description
 * @date 2021/8/1
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户列表
     *
     * @param dto dto
     * @return {@link R}
     */
    R listPage(UserListDTO dto);
}
