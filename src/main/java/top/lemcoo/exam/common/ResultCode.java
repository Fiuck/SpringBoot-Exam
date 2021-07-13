package top.lemcoo.exam.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {

    SUCCESS(2000, "操作成功"),
    LOGOUT_SUCCESS(2001, "注销成功"),
    FAILED(3000, "操作失败"),
    VALIDATE_FAILED(3001, "参数校验失败"),
    UNAUTHORIZED(1000, "请先登录"),
    FORBIDDEN(1001, "没有相关权限"),
    LOGINFAILED(1002, "登录失败");

    /** 定义状态码 */
    private long code;

    /** 返回信息 */
    private String message;

    public long getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
