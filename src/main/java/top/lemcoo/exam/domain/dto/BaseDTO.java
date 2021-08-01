package top.lemcoo.exam.domain.dto;

import lombok.Data;

/**
 * @author zhaowx
 * @Description
 * @date 2021/8/1
 */
@Data
public class BaseDTO {

    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页数量
     */
    private Integer size;
}
