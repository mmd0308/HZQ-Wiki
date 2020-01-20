package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.model.dto.system.UserInfo;
import com.hzqing.admin.service.system.IAuthService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzqing
 * @date 2019-05-17 10:39
 */
@Api(tags = "权限管理")
@Slf4j
@RestController
@RequestMapping("/api/wiki/auth")
public class AuthController extends BaseController {

    @Autowired
    private IAuthService authService;

    @GetMapping("/user")
    public RestResult<UserInfo> getUserInfo(){
        RestResult<UserInfo> result = RestResultFactory.getInstance().success();
        try{
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            result.setData(userInfo);
        }catch (Exception e){
            log.error("AuthController.userInfo occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
       return result;
    }


    @GetMapping("/error")
    public ResponseMessage error(){
        Map map = new HashMap();
        map.put("a","没有权限");
        return responseMessage(map);
    }

}
