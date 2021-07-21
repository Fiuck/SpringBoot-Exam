package top.lemcoo.exam.common.exception;

import top.lemcoo.exam.common.ResultCode;

/**
 * 基础异常
 * @author zhaowx
 * @date 2021/7/21 0021
 */
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;


    /**
     * 错误码
     */
    private long code;


    /**
     * 错误消息
     */
    private String message;

    public BaseException(long code,String message)
    {
        this.code = code;
        this.message = message;
    }

    public BaseException(String message){
        this.message = message;
        this.code = ResultCode.FAILED.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }


    public long getCode() {
        return code;
    }

}
