package top.lemcoo.exam.security;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.common.ResultCode;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.utils.JwtTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 【注销成功处理类】
 *
 * @author zhaowx
 * @date 2021/7/13 0013 8:34
 */
@Component
@Slf4j
public class MyAuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenUtil.getLoginUser(request);
        if(loginUser != null){
            String username = loginUser.getUsername();
            // 删除用户缓存
            tokenUtil.delLoginUser(loginUser.getToken());
            log.info("用户：{},退出登录！", username);
        }

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        String result = JSONUtil.toJsonStr(R.ok(null, ResultCode.LOGOUT_SUCCESS));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
