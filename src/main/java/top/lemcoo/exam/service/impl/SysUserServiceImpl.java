package top.lemcoo.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.domain.dto.UserListDTO;
import top.lemcoo.exam.domain.entity.SysUser;
import top.lemcoo.exam.mapper.SysUserMapper;
import top.lemcoo.exam.service.ISysUserService;

/**
 * 系统用户服务impl
 *
 * @author zhaowx
 * @Description
 * @date 2021/8/1
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 分页查询用户列表
     *
     * @param dto dto
     * @return {@link R}
     */
    @Override
    public R listPage(UserListDTO dto) {
        Page<SysUser> page = new Page<>(dto.getCurrent(), dto.getSize());
        //IPage<Object> list = userMapper.selectPage(page, );
        return null;
    }
}
