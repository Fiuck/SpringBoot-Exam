package top.lemcoo.exam.common;

/**
 * 用户常量信息
 *
 * @author zhaowx
 * @Description
 * @date 2021/7/31
 */
public class UserConstants {

    /**
     * 平台内系统用户的唯一标志
     */
    public static final String SYS_USER = "SYS_USER";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_DISABLE = "1";

    /** 角色封禁状态 */
    public static final String ROLE_DISABLE = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /** 部门停用状态 */
    public static final String DEPT_DISABLE = "1";

    /** 字典正常状态 */
    public static final String DICT_NORMAL = "0";

    /** 是否为系统默认（是） */
    public static final String YES = "Y";

    /** 是否菜单外链（是） */
    public static final Integer YES_FRAME = 1;

    /** 是否菜单外链（否） */
    public static final Integer NO_FRAME = 0;

    /** 菜单类型（目录） */
    public static final Integer TYPE_DIR = 0;

    /** 菜单类型（菜单） */
    public static final Integer TYPE_MENU = 1;

    /** 菜单类型（按钮） */
    public static final Integer TYPE_BUTTON = 2;

    /** Layout组件标识 */
    public final static String LAYOUT = "Layout";

    /** ParentView组件标识 */
    public final static String PARENT_VIEW = "ParentView";

    /** InnerLink组件标识 */
    public final static String INNER_LINK = "InnerLink";

    /** 校验返回结果码 */
    public final static String UNIQUE = "0";
    public final static String NOT_UNIQUE = "1";
}
