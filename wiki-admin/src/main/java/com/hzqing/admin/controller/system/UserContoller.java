package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageHelper;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.domain.space.Space;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, User user){
        startPage(pageNum,pageSize);
        List<User> users = userService.selectList(user);
        return responseMessage(users);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody User user){
        int res = -1;
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
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
