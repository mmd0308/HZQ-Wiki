package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.system.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 10:39
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @PostMapping("/login")
    public ResponseMessage login(String username,String password){
        return responseMessage(username + "-----" + password);
    }

    @GetMapping("/userInfo")
    public ResponseMessage userInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("jj");
        List<String> roles = new ArrayList<>();
        roles.add("Role_admin");
        userInfo.setRoles(roles);
        return responseMessage(userInfo);
    }
}
