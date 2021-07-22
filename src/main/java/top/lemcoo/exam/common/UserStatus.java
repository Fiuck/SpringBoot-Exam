package top.lemcoo.exam.common;

/**
 * 用户状态枚举
 *
 * @author zhaowx
 * @date 2021/7/21
 */
public enum UserStatus {
    OK("0", "正常"),
    DISABLE("1", "停用"),
    DELETED("1", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
