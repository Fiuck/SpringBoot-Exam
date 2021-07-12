package top.lemcoo.exam.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import top.lemcoo.exam.security.jwt.JwtUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【】
 *
 * @author zhaowx
 * @date 2021/6/28 0028 9:34
 */
public class BaseController {

    HttpServletRequest request;

    HttpServletResponse response;


    public static JwtUser getUser(){
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}