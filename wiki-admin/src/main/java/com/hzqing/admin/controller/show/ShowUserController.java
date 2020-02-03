package com.hzqing.admin.controller.show;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.converter.system.UserConverter;
import com.hzqing.admin.model.entity.system.User;
import com.hzqing.admin.model.params.system.UserRegister;
import com.hzqing.admin.service.system.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author hzqing
 * @date 2020-02-03 13:33
 */
@Api(tags = "标签管理模块")
@Slf4j
@RestController
@RequestMapping("/api/wiki/show/users")
public class ShowUserController extends BaseController{

    @Autowired
    private IUserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public RestResult register(@RequestBody UserRegister userRegister){
        RestResult result = RestResultFactory.getInstance().success();
        try{
            User user = userConverter.userRegisterToUser(userRegister);
            user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
            userService.create(user);
        }catch (Exception e){
            log.error("ShowUserController.register occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }


    @ApiOperation(value = "检查登陆账号是否可用")
    @GetMapping("/{username}/username")
    public RestResult checkUsername(@PathVariable String username){
        RestResult result = RestResultFactory.getInstance().success();
        result.setData(false);
        try {
            User user = userService.getByUserName(username);
            if (null == user) {result.setData(true);}
        }catch (Exception e){
            log.error("ShowUserController.checkUsername occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

}
