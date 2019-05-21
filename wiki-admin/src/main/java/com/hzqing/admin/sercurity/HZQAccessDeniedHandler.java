package com.hzqing.admin.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.common.HttpStatus;
import com.hzqing.admin.common.ResponseMessage;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HZQAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    /**
     * 请求没有权限，自定义返回信息
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN);

        // 没有权限的时候，返回响应数据
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(HttpStatus.FORBIDDEN);
        responseMessage.setMessage("没有访问权限");

        String json = new ObjectMapper().writeValueAsString(responseMessage);

        response.setContentType("application/josn");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
