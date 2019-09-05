package com.hzqing.admin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.common.ConstantSecurity;
import com.hzqing.admin.common.HttpStatus;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.JwtTokenUtil;
import com.hzqing.admin.domain.system.UserInfo;
import com.hzqing.admin.sercurity.service.UserInfoDetailsService;
import com.hzqing.admin.service.system.IAuthService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 校验token是否正确
 * 在JWTLoginFilter中生成token，在该拦截器中校验token是否正确
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author hzqing
 * @date 2019/05/21 10:37
 */

public class JWTAuthFilter extends BasicAuthenticationFilter {

    private IAuthService authService;

    private UserInfoDetailsService userInfoDetailsService;

    private final static Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);

    public JWTAuthFilter(AuthenticationManager authenticationManager, UserInfoDetailsService userInfoDetailsService, IAuthService authService) {
        super(authenticationManager);
        this.userInfoDetailsService = userInfoDetailsService;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 平台token
        String token = request.getHeader(ConstantSecurity.TOKEN_KEY);
        // token不存在，直接返回
        if (token == null || !token.startsWith(ConstantSecurity.TOKEN_PREFIX)) {
            // 如果不是api开头的，直接放行
            if (!request.getRequestURI().startsWith("/api")) {
                System.out.println("request = [" + request.getRequestURL() + "], response = [" + response + "], chain = [" + chain + "]");
                chain.doFilter(request, response);
                return;
            }
            // 自定义返回信息
            ResponseMessage responseMessage = new ResponseMessage();
            response.setContentType("text/json;charset=utf-8");
            responseMessage.setMessage("登陆超时，请重新登陆！");
            responseMessage.setCode(HttpStatus.FORBIDDEN);
            String json = new ObjectMapper().writeValueAsString(responseMessage);
            response.getWriter().write(json);
            return;
        }
        // 检验token是否正确
        // 从token中获取用户名
        String userName = JwtTokenUtil.getUsernameFromToken(token.replace(ConstantSecurity.TOKEN_PREFIX,""));
        if (StringUtils.isEmpty(userName)){
            logger.error("hzqing.com --- 该Token错误，请重新登陆");
            return;
        }
        logger.info("hzqing.com --- 从新配置 SecurityContextHolder");
        UserInfo userInfo = authService.getUserInfo(userName);
        userInfo.setToken(token);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo,userInfo.getPassword(), AuthorityUtils.createAuthorityList(userInfo.getResources()));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }
}
