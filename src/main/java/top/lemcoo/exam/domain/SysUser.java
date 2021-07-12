package top.lemcoo.exam.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:18
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class SysUser {

    private Long id;

    private String username;

    private String password;

    /**
     * 头像
     */
    private String avatar;

    private String email;

    private String city;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    /**
     * 该用户的角色集合
     */
    @TableField(exist = false)
    private Set<SysRole> roles;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 是否禁用，0-禁用，1-启用
     */
    @TableField("status")
    private Integer status;

}
