package top.lemcoo.exam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 城市
     */
    private String city;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 状态
     * 是否禁用，0-禁用，1-启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 逻辑删除，0-已删除，1-正常
     */
    private Integer delFlag;

    /**
     * 是否为admin
     * @return
     */
    public boolean isAdmin(){
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId){
        return userId != null && 1L == userId;
    }

}
