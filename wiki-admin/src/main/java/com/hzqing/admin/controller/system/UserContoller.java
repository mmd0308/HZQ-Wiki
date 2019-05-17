package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageHelper;
import com.hzqing.admin.domain.system.User;
import com.hzqing.admin.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:29
 */
@RestController
@RequestMapping("/user")
public class UserContoller {
    @Autowired
    private IUserService userService;

    @GetMapping("/page")
    public List<User> page(int pageNum, int pageSize, User user){
        PageHelper.startPage(pageNum == 0 ? 1 : pageNum, pageSize == 0 ? 10 : pageSize);
        List<User> users = userService.selectList(user);
        return users;
    }
}
