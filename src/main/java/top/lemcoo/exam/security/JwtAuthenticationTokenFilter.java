package top.lemcoo.exam.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.lemcoo.exam.domain.model.LoginUser;
import top.lemcoo.exam.utils.JwtTokenUtil;
import top.lemcoo.exam.service.MyUserDetailsService;
import top.lemcoo.exam.utils.SecurityUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 【拦截器】
 *
 * @author zhaowx
 * @date 2021/6/25 0025 8:46
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String token = request.getHeader(jwtTokenUtil.getHeader());
        LoginUser loginUser = jwtTokenUtil.getLoginUser(request);
        if (loginUser != null && SecurityUtils.getAuthentication() == null){
            jwtTokenUtil.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
//        if (!StringUtils.isEmpty(token)) {
//            String username = jwtTokenUtil.getUsernameFromToken(token);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
//                if (jwtTokenUtil.validateToken(token, userDetails)){
//                    // 将用户信息存入 authentication，方便后续校验
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
        chain.doFilter(request, response);
    }
}
