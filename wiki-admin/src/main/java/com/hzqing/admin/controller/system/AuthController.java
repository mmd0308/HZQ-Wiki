package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.system.UserInfo;
import com.hzqing.admin.service.system.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzqing
 * @date 2019-05-17 10:39
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private IAuthService authService;

    @GetMapping("/userInfo")
    public ResponseMessage userInfo(){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return responseMessage(userInfo);
    }
}
