package com.hzqing.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.hzqing.admin.domain.system.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 是否为演示系统，进行拦截
 */
@Component
@Slf4j
public class ShowInterceptor implements HandlerInterceptor {

    /**
     * 是否为演示系统
     */
    @Value("${hzq.show}")
    private boolean show;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ShowInterceptor.preHandle " + request.getServletPath());

        if (show){
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("ShowInterceptor.preHandle " + userInfo);
            // 非get请求，进行操作
            if(!request.getMethod().matches(HttpMethod.GET.name())
                && !userInfo.getUsername().equals("admin")
                && !userInfo.getUsername().equals("hengzhaoqing")
            ){
                HashMap<String,String> res = new HashMap<>();
                res.put("code","500");
                res.put("message","演示系统，禁止修改");
                String json = JSON.toJSONString(res);
                response.setContentType("application/josn");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                return false;
            }
        }
        return true;
    }

}
