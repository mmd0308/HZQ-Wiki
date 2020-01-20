package com.hzqing.admin.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.common.constants.ConstantSecurity;
import com.hzqing.admin.common.HttpStatus;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.JwtTokenUtil;
import com.hzqing.admin.domain.log.LoginLog;
import com.hzqing.admin.model.dto.system.UserInfoDetails;
import com.hzqing.admin.service.log.ILoginLogService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author hzqing
 * @date 2018/10/27 19:24
 */
@Component
public class HZQAuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 登陆成功之后，返回信息
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

        // 记录登陆信息
        this.addLoginLog(request,user);

        claims.put("iss",user.getPassword()); //jwt的签发者 密码
        claims.put("sub",user.getUsername()); // JWT所面向的用户 用户的昵称
        claims.put("iat",new Date());
        claims.put("jti",user.getUserId()); //jwt的唯一身份表示
        // 生成token
        String token = JwtTokenUtil.generateToken(claims, ConstantSecurity.JWT_EXPIRATION);

        // 登陆成功。返回Token
        RestResult result = RestResultFactory.getInstance().success();
        result.setData(ConstantSecurity.TOKEN_PREFIX + token);


        response.addHeader(ConstantSecurity.TOKEN_KEY, ConstantSecurity.TOKEN_PREFIX + token);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers ",ConstantSecurity.TOKEN_KEY + "," + "Access-Control-Allow-Origin");

        String json = new ObjectMapper().writeValueAsString(result);
        response.setContentType("application/josn");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 新增一条登陆日志
     * @param request
     * @param user
     */
    public void addLoginLog(HttpServletRequest request, UserInfoDetails user){

        //  解析浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取浏览器信息
        Browser browser = userAgent.getBrowser();
        // 获取浏览器版本号
        Version version = userAgent.getBrowserVersion();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 登陆成功，记录登陆信息
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(user.getUsername());
        loginLog.setIp(request.getRemoteAddr());
        loginLog.setBrowser(browser.getName() + "/" + version.getVersion());
        loginLog.setCreateBy(user.getUserId());
        loginLog.setCreateTime(LocalDateTime.now());
        loginLog.setSystem(operatingSystem.getManufacturer() + " " + operatingSystem.getDeviceType().name() + " " + operatingSystem.getName());
        loginLogService.insert(loginLog);
    }
}
