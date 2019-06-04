package com.hzqing.admin.controller.system;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:29
 */
@RestController
@RequestMapping("/user")
public class UserContoller extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, User user){
        startPage(pageNum,pageSize);
        List<User> users = userService.selectList(user);
        return responseMessage(users);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody User user){
        user.setCreateBy(-1);
        user.setUpdateBy(-1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int res = userService.insert(user);
        return responseMessage(res);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody User user){
        int res = -1;
        user = (User) initAddOrUpdate(user);
        if ( user.getId() ==0){ //新增
            res = userService.insert(user);
        }else {
            res = userService.update(user);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = userService.deletedById(id);
        return responseMessage(res);
    }
}
