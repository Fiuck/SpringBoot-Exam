package top.lemcoo.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:19
 * @desc
 */
@Data
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu {

    private Long id;

    private Long parentId;

    private String name;

    private String path;

    private String perms;

    private String component;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;
}
