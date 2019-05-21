package com.hzqing.admin.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.common.ConstantSecurity;
import com.hzqing.admin.common.HttpStatus;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.JwtTokenUtil;
import com.hzqing.admin.domain.system.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author hzqing
 * @date 2018/10/27 19:24
 */
@Component
public class HZQAuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler {


    /**
     *
     * @param request
     * @param response
     * @param authentication 代表认证成功之后的信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //生成jwt
        Map<String, Object> claims = new HashMap<String, Object>();
        // 获取用户登陆信息
        UserInfoDetails user = (UserInfoDetails) authentication.getPrincipal();

        claims.put("iss",user.getPassword()); //jwt的签发者 密码
        claims.put("sub",user.getUsername()); // JWT所面向的用户 用户的昵称
        claims.put("iat",new Date());
        claims.put("jti",user.getUserId()); //jwt的唯一身份表示
        // 生成token
        String token = JwtTokenUtil.generateToken(claims, ConstantSecurity.JWT_EXPIRATION);


        // 登陆成功。返回Token
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(HttpStatus.OK);
        responseMessage.setData(ConstantSecurity.TOKEN_PREFIX + token);
        response.addHeader(ConstantSecurity.TOKEN_KEY, ConstantSecurity.TOKEN_PREFIX + token);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers ",ConstantSecurity.TOKEN_KEY + "," + "Access-Control-Allow-Origin");
        String json = new ObjectMapper().writeValueAsString(responseMessage);
        response.setContentType("application/josn");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
