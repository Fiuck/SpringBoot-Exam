package top.lemcoo.exam.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {

    // 1xxx “登录认证” 或 “鉴权” 方面及“用户方面”状态码
    UNAUTHORIZED(1000, "请先登录"),
    FORBIDDEN(1001, "没有相关权限，请联系管理员授权"),
    LOGINFAILED(1002, "用户名或密码不正确"),

    // 2xxx "成功状态码"
    SUCCESS(2000, "操作成功"),
    LOGOUT_SUCCESS(2001, "注销成功"),

    // 3xxx 业务方面状态码
    FAILED(3000, "操作失败"),
    VALIDATE_FAILED(3001, "参数校验失败"),

    // 4xxx 路径问题等状态码
    NOTFOUND(4000, "路径不存在，请检查路径是否正确");

    /** 定义状态码 */
    private Integer code;

    /** 返回信息 */
    private String message;

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
