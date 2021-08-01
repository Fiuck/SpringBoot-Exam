package top.lemcoo.exam.service;

import top.lemcoo.exam.common.R;

/**
 * @author zhaowx
 * @Description
 * @date 2021/7/20
 */
public interface ISysLoginService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取登录的用户信息
     *
     * @return
     */
    R getInfo();

    /**
     * 获取用户路由
     *
     * @return
     */
    R getRoutes();
}
