package com.hzqing.admin.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.common.HttpStatus;
import com.hzqing.admin.common.ResponseMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败之后的返回
 * @author hzqing
 * @date 2018/10/27 19:30
 */
@Component
public class HZQAuthenticationFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 自定义返回信息
        ResponseMessage responseMessage = new ResponseMessage();
        String msg = "返回错误提示信息";
        int code = HttpStatus.OK;

        response.setContentType("text/json;charset=utf-8");
        if (exception instanceof BadCredentialsException) { // 密码错误
            msg = "登陆用户名或者密码错误";
            code = HttpStatus.FORBIDDEN;
        }
        responseMessage.setMessage(msg);
        responseMessage.setCode(code);
        String json = new ObjectMapper().writeValueAsString(responseMessage);
        response.getWriter().write(json);
    }
}
