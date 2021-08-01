package top.lemcoo.exam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:19
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 路径
     */
    private String path;

    /**
     * 权限
     */
    private String perms;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否显示，0-不显示，1-显示
     */
    private Integer visible;

    /**
     * 是否缓存（0不缓存 1缓存）
     */
    private Integer isCache;

    /**
     * 是否为外联，0-否，1-是
     */
    private Integer isFrame;

    /**
     * 逻辑删除，0-已删除，1-正常
     */
    private Integer delFlag;

    /**
     * 子菜单列表
     */
    @TableField(exist = false)
    private List<SysMenu> children;
}
