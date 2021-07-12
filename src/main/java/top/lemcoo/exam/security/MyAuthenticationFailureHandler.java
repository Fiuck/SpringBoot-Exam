package top.lemcoo.exam.security;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import top.lemcoo.exam.common.R;
import top.lemcoo.exam.common.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 【自定义登陆失败的操作】
 *
 * @author zhaowx
 * @date 2021/6/25 0025 8:39
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        String result = JSONUtil.toJsonStr(R.error(ResultCode.LOGINFAILED));
        outputStream.write(result.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
