package top.lemcoo.exam.common.exception;

/**
 * 用户异常类
 * @author zhaowx
 * @date 2021/7/21 0021
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(long code, String message)
    {
        super(code, message);
    }
}