package top.lemcoo.exam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:19
 * @desc
 */
@Data
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long menuId;

    private Long parentId;

    private String menuName;

    private String path;

    private String perms;

    private String component;

    private Integer menuType;

    private String icon;

    private Integer orderNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;

    /**
     * 逻辑删除，0-已删除，1-正常
     */
    private Integer delFlag;
}
