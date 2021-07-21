package top.lemcoo.exam.common.exception;

import top.lemcoo.exam.common.ResultCode;

/**
 * 用户密码不正确或不符合规范异常类
 * @author zhaowx
 * @date 2021/7/21 0021
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super(ResultCode.LOGINFAILED.getCode(), ResultCode.LOGINFAILED.getMessage());
    }
}
