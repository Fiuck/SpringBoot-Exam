package top.lemcoo.exam.common;


import java.io.Serializable;
import java.util.HashMap;

/**
 * 【响应消息主体】
 *
 * @Author: zhaowx
 * @Date: 2021/6/3 18:54
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;

    public static <T> R<T> ok()
    {
        return restResult(null, ResultCode.SUCCESS.getCode(), null);
    }

    public static <T> R<T> ok(T data)
    {
        return restResult(data, ResultCode.SUCCESS);
    }

    public static <T> R<T> ok(T data, ResultCode resultCode){
        return restResult(data,resultCode);
    }

    public static <T> R<T> ok(T data, String msg)
    {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> error(ResultCode resultCode){
        return restResult(null,resultCode);
    }

    public static <T> R<T> error(String msg){
        return error(null, ResultCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> error()
    {
        return restResult(null, ResultCode.FAILED);
    }

    public static <T> R<T> error(T data,Integer code, String msg)
    {
        return restResult(data, code, msg);
    }

    private static <T> R<T> restResult(T data, Integer code, String msg)
    {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
    private static <T> R<T> restResult(T data, ResultCode r){
        R<T> apiResult = new R<>();
        apiResult.setData(data);
        apiResult.setCode(r.getCode());
        apiResult.setMsg(r.getMessage());
        return apiResult;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

}
