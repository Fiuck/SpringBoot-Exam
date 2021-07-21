package top.lemcoo.exam.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhaowx
 * @date 2021/6/24 0024 10:18
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class SysRole {

    private Long roleId;

    private String roleName;

    private String code;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer status;
}
