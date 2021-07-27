package top.lemcoo.exam.domain.model;

import lombok.Data;


/**
 * 登录实体
 *
 * @author zhaowx
 * @date 2021/07/27
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

//    private String
}
